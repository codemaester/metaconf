package metaconf.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import metaconf.core.configuration.Configuration;
import metaconf.core.configuration.ConfigurationNodeLink;
import metaconf.core.configuration.Context;
import metaconf.core.model.DataNode;
import metaconf.core.model.GroupNode;
import metaconf.core.model.ValueType;
import metaconf.core.scope.Scope;
import metaconf.core.scope.ScopeType;
import metaconf.core.scope.ScopedValue;
import metaconf.core.values.BooleanConfigurationValue;
import metaconf.core.values.ConfigurationValue;
import metaconf.core.values.IntegerConfigurationValue;
import metaconf.core.values.StringConfigurationValue;

import org.junit.Before;
import org.junit.Test;

public class JSONBindingTest {

    private JAXBContext jaxbContext;
    private Unmarshaller unmarshaller;
    private Marshaller marshaller;
	
    @Before
    public void init() throws JAXBException {
        this.jaxbContext = JAXBContext.newInstance(DataNode.class, 
        		GroupNode.class, ScopeType.class, Scope.class, ScopedValue.class,
        		IntegerConfigurationValue.class, StringConfigurationValue.class,
        		BooleanConfigurationValue.class, Context.class, 
        		ConfigurationNodeLink.class, Configuration.class);
        this.marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty("eclipselink.media-type", "application/json");
        this.unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setProperty("eclipselink.media-type", "application/json");
    }

    @Test
    public void dataNode() throws JAXBException {
        DataNode node = new DataNode();
        node.setName("DataNode");
        node.setType(ValueType.INTEGER);      
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
    public void scopeType() throws JAXBException {
    	ScopeType scopeType = ScopeType.builder().priority(1).name("dimension_1").build();
    	verifyMarshalling(scopeType);
    }

    @Test
    public void scope() throws JAXBException {
    	Scope scope = Scope.builder().name("dimension_1").value("value").build();
    	verifyMarshalling(scope);
    }
    
    @Test
    public void scopedValue() throws JAXBException {
    	ScopedValue confValue = new ScopedValue();
    	verifyMarshalling(confValue);
    }

    @Test
    public void configurationValueString() throws JAXBException {
    	ScopedValue confValue = createScopedValue();
    	confValue.setValue(ConfigurationValue.newStringValue("str_val"));
    	verifyMarshalling(confValue);
    }
    
    @Test
    public void configurationValueBoolean() throws JAXBException {
    	ScopedValue confValue = createScopedValue();
    	confValue.setValue(ConfigurationValue.newBooleanValue(true));
    	verifyMarshalling(confValue);
    }
    
    @Test
    public void configurationValueInteger() throws JAXBException {
    	ScopedValue confValue = createScopedValue();
    	confValue.setValue(ConfigurationValue.newIntegerValue(123456));
    	verifyMarshalling(confValue);
    }
    
    @Test
    public void context() throws JAXBException {
    	Context context = new Context();
    	context.setScopeValue("dimension_1", "dvalue1");
    	verifyMarshalling(context);
    }

    @Test
    public void configurationNodeLink() throws JAXBException {
    	ConfigurationNodeLink configLink = new ConfigurationNodeLink();
    	configLink.setNodeId(1234L);
    	ScopedValue confValue = createScopedValue();
    	confValue.setValue(ConfigurationValue.newIntegerValue(123456));
    	configLink.setScopedValues(Arrays.asList(confValue));
    	verifyMarshalling(configLink);
    }
    
    @Test
    public void configuration() throws JAXBException {
    	ConfigurationNodeLink configLink = new ConfigurationNodeLink();
    	configLink.setNodeId(1234L);
    	ScopedValue confValue = createScopedValue();
    	confValue.setValue(ConfigurationValue.newIntegerValue(123456));
    	configLink.setScopedValues(Arrays.asList(confValue));
    	Configuration config = new Configuration();
    	config.getConfigurationValues().add(configLink);
    	verifyMarshalling(config);
    }
    
	private ScopedValue createScopedValue() {
		ScopedValue confValue = new ScopedValue();
    	confValue.setScopeValue("dimension_1", "dvalue1");
    	confValue.setScopeValue("dimension_2", "dvalue2");
		return confValue;
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
        System.out.println(new String(content));

        ByteArrayInputStream bais = new ByteArrayInputStream(content);
        Object copy = this.unmarshaller.unmarshal(bais);
        
        assertNotSame(node, copy);
        assertEquals(node, copy);
	}
	
}
