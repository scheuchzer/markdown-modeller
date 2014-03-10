PersistenceXml(model) ::= <<
\<?xml version="1.0" encoding="UTF-8" ?\>
\<persistence version="2.1"
   xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://www.oracle.com/webfolder/technetwork/jsc/xml/ns/persistence/persistence_2_1.xsd"\>
   \<persistence-unit name="<model.unitName>" transaction-type="<model.transactionType>"\>
      <model.classes:{c|\<class\><c.name>\</class\>
      }>
      \<exclude-unlisted-classes\><model.excludeUnlistedClasses>\</exclude-unlisted-classes\>
      \<properties\>
         <model.properties:{prop|
         \<property name="<prop.key>" value="<prop.value>" /\>
}>      \</properties\>
   \</persistence-unit\>
\</persistence\>
>>