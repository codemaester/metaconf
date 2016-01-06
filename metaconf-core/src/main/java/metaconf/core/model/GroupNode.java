package metaconf.core.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name ="group")
@XmlSeeAlso(Node.class)
public class GroupNode implements Node {
	private String name;

	
	@XmlElementRefs({ @XmlElementRef(name = "group", type = GroupNode.class),
			@XmlElementRef(name = "data", type = DataNode.class) })
	private List<Node> children = new ArrayList<>();
}
