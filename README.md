# camel-fwk
A Karaf / Camel framework allowing jms2ws and ws2jms connection

This framework has been designed to work with karaf 4 and camel 2.16.5.

There are two main generics route allowing communication with rest WS and JMS (active MQ and websphere MQ).

You just have to correctly fill the .cfg with amq or wmq

A main bundle fwk-prowy-webservice holds the main route.

The fwk-common bundle is able to persist the payload of an exchange in order to retrieve it by WS (REST with CXF)
