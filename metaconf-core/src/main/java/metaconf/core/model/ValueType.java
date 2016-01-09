package metaconf.core.model;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum ValueType {
	BOOLEAN,
	INTEGER,
	STRING,
	JSON,
	XML
}
