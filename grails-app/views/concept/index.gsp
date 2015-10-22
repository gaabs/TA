
<%@ page import="ta.Concept" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'concept.label', default: 'Concept')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-concept" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
<<<<<<< HEAD
				<li><g:link class="create" action="upload"><g:message code="default.new.label" args="Upload Sheet"/> </g:link>Upload Sheet</li>
=======
				<li><g:link class="create" action="upload"><g:message code="default.new.label" message="Upload Sheet"/> </g:link></li>
>>>>>>> 6e87b84962abbf18b068e9aef5aa0b8ae9cb1c47
			</ul>
		</div>
		<div id="list-concept" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="concept" title="${message(code: 'concept.concept.label', default: 'Concept')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${conceptInstanceList}" status="i" var="conceptInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${conceptInstance.id}">${fieldValue(bean: conceptInstance, field: "concept")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${conceptInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
