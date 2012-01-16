<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<f:view>
<html>
	<head>
	</head>
	<body>
		<rich:tabPanel id="mainPanel">
			<rich:tab label="#{msg['tab.persons']}" labelWidth="120">
				<jsp:include page="personsTable.jsp"/>
				<jsp:include page="personForm.jsp"/>
				<jsp:include page="importPersons.jsp"/>
			</rich:tab>
			<rich:tab label="#{msg['tab.sms']}" labelWidth="120">
				<jsp:include page="sms.jsp" flush="true"/>
			</rich:tab>
			<rich:tab label="#{msg['tab.messages']}" labelWidth="120">
				<jsp:include page="messagesTable.jsp"/>
			</rich:tab>
		</rich:tabPanel>
	</body>
</html>
</f:view>
