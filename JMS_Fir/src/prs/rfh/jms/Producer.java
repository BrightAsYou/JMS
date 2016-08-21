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
	
	 	static final String USERNAME = ActiveMQConnection.DEFAULT_USER;//Ĭ�������û���
	    static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;//Ĭ����������
	    static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;//Ĭ�����ӵ�ַ
	    static final int SENDNUM = 15;
	    public static void main(String[] args) {
	        ConnectionFactory connectionFactory;//���ӹ���
	        Connection connection = null;//����

	        Session session;//�Ự ���ܻ��߷�����Ϣ���߳�
	        Destination destination;//��Ϣ��Ŀ�ĵ�
	        Destination destination1;//��Ϣ��Ŀ�ĵ�
	        MessageConsumer messageConsumer;//��Ϣ��������
	        MessageProducer messageProducer;
	        //ʵ�������ӹ���
	        connectionFactory = new ActiveMQConnectionFactory(Consumer.USERNAME, Consumer.PASSWORD, Consumer.BROKEURL);

	        try {
	            //ͨ�����ӹ�����ȡ����
	            connection = connectionFactory.createConnection();
	            //��������
	            connection.start();
	            //����session
	            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	            //����һ������HelloWorld����Ϣ����
//	            destination = session.createQueue("HelloWorld");
	            destination1 = session.createQueue("HelloWorld1");
	            //������Ϣ������
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
//	                    System.out.println("�յ�����Ϣ:" + textMessage.getText());
//	                }else {
////	                    break;
//	                }
//	            }


	        } catch (JMSException e) {
	            e.printStackTrace();
	        }

	    }
	
}
