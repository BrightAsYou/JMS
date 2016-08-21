package prs.rfh.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

public class Producer {
	
	 	static final String USERNAME = ActiveMQConnection.DEFAULT_USER;//默认连接用户名
	    static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;//默认连接密码
	    static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;//默认连接地址
	    static final int SENDNUM = 15;
	    public static void main(String[] args) {
	        ConnectionFactory connectionFactory;//连接工厂
	        Connection connection = null;//连接

	        Session session;//会话 接受或者发送消息的线程
	        Destination destination;//消息的目的地
	        Destination destination1;//消息的目的地
	        MessageConsumer messageConsumer;//消息的消费者
	        MessageProducer messageProducer;
	        //实例化连接工厂
	        connectionFactory = new ActiveMQConnectionFactory(Consumer.USERNAME, Consumer.PASSWORD, Consumer.BROKEURL);

	        try {
	            //通过连接工厂获取连接
	            connection = connectionFactory.createConnection();
	            //启动连接
	            connection.start();
	            //创建session
	            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	            //创建一个连接HelloWorld的消息队列
//	            destination = session.createQueue("HelloWorld");
	            destination1 = session.createQueue("HelloWorld1");
	            //创建消息消费者
	            messageConsumer = session.createConsumer(destination1);
	            messageProducer = session.createProducer(destination1);
	            Message msg = new ActiveMQTextMessage();
	            msg.setStringProperty("stringproerty", "hello world !");
	            messageProducer.send(msg);
	            TextMessage textMessage = (TextMessage) messageConsumer.receive(20000);
	            System.out.println(textMessage.getStringProperty("stringproerty"));
//	            while (true) {
//	                TextMessage textMessage = (TextMessage) messageConsumer.receive(100000);
//	                if(textMessage != null){
//	                    System.out.println("收到的消息:" + textMessage.getText());
//	                }else {
////	                    break;
//	                }
//	            }


	        } catch (JMSException e) {
	            e.printStackTrace();
	        }

	    }
	
}
