package ram.jms;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Main {

    //Nombre de la cola de la que recibe el JSON (Mi cola)
    private final static String QUEUE_NAME = "cola_cuentasporcobrar";
    //Nombre de la cola a la que se envia el JSON recbido (Cola Procesamiento Ordenes)
    private final static String QUEUE_NAME2 = "test_queue";


    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    //String fecha = dtf.format(LocalDateTime.now()).substring(0,10);

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    int aleatorio = getRandomNumber(1, 7);

    // parse date from yyyy-mm-dd pattern
    LocalDate hoy = LocalDate.now();
    String fecha_pedido = dtf.format(hoy).substring(0,10);
    String fecha_entrega = dtf.format(hoy.plusDays(aleatorio)).substring(0,10);


    //Para la conexión a la base de datos
    JDBCConector conector = new JDBCConector();

    public static void main(String[] argv) throws Exception {

        //Para convertirlo a JSON

        Main teorico = new Main();

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("beaver-01.rmq.cloudamqp.com");
        factory.setUsername("pavrucat");
        factory.setPassword("QXLlQj6EJRJmy7eahmDg0DKfkF3w_zX6");
        factory.setVirtualHost("pavrucat");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        System.out.println(
                " [*] Waiting for messages. To exit press CTRL+C");


        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            //RECIBO EL MENSAJE
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            //MODIFICO EL MENSAJE
            //--------Paso de String a JSON
            JSONObject root = new JSONObject(message);
            JSONArray lista_productos = root.getJSONArray("detalles");
            //--------Agregamos nuestros 3 campos
            root.put("origen","cuentasporcobrar");
            root.put("fecha_cobro",teorico.fecha_pedido);
            root.put("fecha_entrega",teorico.fecha_entrega);
            root.put("estado_registro","Pendiente");
            //--------Insertamos en la base de datos
            teorico.conector.insertar(root, lista_productos);
            //--------Lo pasamos a String de nuevo
            String nuevo_message = root.toString();;
            //String nuevo_message = message + " " + teorico.fecha;
            //REENVÍO EL MENSAJE
            channel.basicPublish("exchange_procesosnegocio", "key_cola_procesamientoordenes", null,
                    nuevo_message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + nuevo_message + "'");

        };

        channel.basicConsume(QUEUE_NAME, true, deliverCallback,
                consumerTag -> {
                });
    }
}

