package metaconf.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import metaconf.core.model.DataNode;
import metaconf.core.model.GroupNode;
import metaconf.core.model.Node;
import metaconf.core.model.ValueType;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class DataNodeTest {

    private JAXBContext jaxbContext;
    private Unmarshaller unmarshaller;
    private Marshaller marshaller;
	
    @Before
    public void init() throws JAXBException {
        this.jaxbContext = JAXBContext.newInstance(DataNode.class, GroupNode.class);
        this.marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty("eclipselink.media-type", "application/json");
        this.unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setProperty("eclipselink.media-type", "application/json");
    }

    @Test
    public void serializeDataNode() throws JAXBException {
        DataNode node = new DataNode();
        node.setName("DataNode");
        node.setType(ValueType.DECIMAL);      
        verifyMarshalling(node);
    }
    
    @Test
    public void serializeGroupNode() throws JAXBException {
        GroupNode gnode = new GroupNode();
        gnode.setName("GroupNode");
        
        DataNode node1 = new DataNode();
        node1.setName("DataNode1");
        node1.setType(ValueType.DECIMAL);
        
        DataNode node2 = new DataNode();
        node2.setName("DataNode2");
        node2.setType(ValueType.BOOLEAN);
        
        gnode.getChildren().add(node1);
        gnode.getChildren().add(node2);
        
        verifyMarshalling(gnode);
    }
    

	private void verifyMarshalling(Node node) throws JAXBException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        this.marshaller.marshal(node, baos);

        byte[] content = baos.toByteArray();
        System.out.println(new String(content));

        ByteArrayInputStream bais = new ByteArrayInputStream(content);
        Node copy = (Node)this.unmarshaller.unmarshal(bais);

        assertNotSame(node, copy);
        assertEquals(node, copy);
	}
	
}
