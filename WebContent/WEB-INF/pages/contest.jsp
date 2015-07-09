<%@ page import="com.trinary.aftv.hateoas.ContestResource" %>
<%@ page import="com.trinary.aftv.hateoas.ContestEntryResource" %>
<%@ page import="java.util.List" %>
<html>
	<head>
		<style>
			#entries {
				position: relative;
				left: 50px;
			}
		</style>
	</head>
	<body>
		<%
			ContestResource contest = (ContestResource)request.getAttribute("contest");
			List<ContestEntryResource> entries = (List<ContestEntryResource>)request.getAttribute("entries");
		%>
		
		<h1><%= contest.getUuid() %></h1>
		
		<div id="entries">
			<h3>Entries:</h3>
			<ul>
				<% for (ContestEntryResource resource : entries) { %>
					<li><b><%= resource.getArtist() %></b>- <%= resource.getTitle() %></li>
				<% } %>
			</ul>
		</div>
	</body>	
</html>