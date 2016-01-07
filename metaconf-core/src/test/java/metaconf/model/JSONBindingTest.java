package metaconf.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import metaconf.core.dimensions.ConfigurationValue;
import metaconf.core.dimensions.Dimension;
import metaconf.core.dimensions.DimensionValue;
import metaconf.core.model.DataNode;
import metaconf.core.model.GroupNode;
import metaconf.core.model.ValueType;

import org.junit.Before;
import org.junit.Test;

public class JSONBindingTest {

    private JAXBContext jaxbContext;
    private Unmarshaller unmarshaller;
    private Marshaller marshaller;
	
    @Before
    public void init() throws JAXBException {
        this.jaxbContext = JAXBContext.newInstance(DataNode.class, 
        		GroupNode.class, Dimension.class, DimensionValue.class, ConfigurationValue.class);
        this.marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty("eclipselink.media-type", "application/json");
        this.unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setProperty("eclipselink.media-type", "application/json");
    }

    @Test
    public void dataNode() throws JAXBException {
        DataNode node = new DataNode();
        node.setName("DataNode");
        node.setType(ValueType.DECIMAL);      
        verifyMarshalling(node);
    }
    
    @Test
    public void groupNode() throws JAXBException {
        GroupNode gnode = new GroupNode();
        gnode.setName("GroupNode");
              
        gnode.getChildren().add(createDataNode("DataNode1", ValueType.INTEGER));
        gnode.getChildren().add(createDataNode("DataNode2", ValueType.BOOLEAN));
        
        GroupNode parentNode = new GroupNode();
        parentNode.setName("ParentNode");
        parentNode.getChildren().add(createDataNode("DataNode3", ValueType.STRING));
        parentNode.getChildren().add(gnode);
        
        verifyMarshalling(parentNode);
    }
    
    @Test
    public void dimension() throws JAXBException {
    	Dimension dim = Dimension.builder().priority(1).name("dim1").build();
    	verifyMarshalling(dim);
    }

    @Test
    public void dimensionValue() throws JAXBException {
    	Dimension dim = Dimension.builder().priority(1).name("dim1").build();
    	DimensionValue dimValue = DimensionValue.builder().dimension(dim).value("value").build();
    	verifyMarshalling(dimValue);
    }
    
    @Test
    public void configurationValueEmpty() throws JAXBException {
    	ConfigurationValue confValue = new ConfigurationValue();
    	verifyMarshalling(confValue);
    }
    
	private DataNode createDataNode(String name, ValueType type) {
		DataNode node1 = new DataNode();
        node1.setName(name);
        node1.setType(type);
		return node1;
	}
    

	private void verifyMarshalling(Object node) throws JAXBException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        this.marshaller.marshal(node, baos);

        byte[] content = baos.toByteArray();
        System.out.println(node);
        System.out.println(new String(content));

        ByteArrayInputStream bais = new ByteArrayInputStream(content);
        Object copy = this.unmarshaller.unmarshal(bais);
        
        assertNotSame(node, copy);
        assertEquals(node, copy);
	}
	
}
