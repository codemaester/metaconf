package metaconf.core.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = { "name" })
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name ="data")
@XmlSeeAlso(Node.class)
public class DataNode implements Node {
	private String name;
	private ValueType type;
}