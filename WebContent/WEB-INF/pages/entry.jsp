<%@ page import="com.trinary.aftv.hateoas.ContestEntryResource" %>
<%@ page import="com.trinary.aftv.hateoas.VoteResource" %>
<%@ page import="java.util.List" %>
<html>
	<head>
		<style>
			.container {
				margin: auto;
				width: 500px;
			}
			
			.thumbnail {
				width: 100%;
			}
			
			.title {
				width: 100%;
				font-size: 50px;
			}
		</style>
	</head>
	<body>
		<%
			ContestEntryResource entry = (ContestEntryResource)request.getAttribute("entry");
			List<VoteResource> votes = (List<VoteResource>)request.getAttribute("votes");
		%>
		<div class="container">
			<center><marquee class="title"><%= entry.getArtist() %>- <%= entry.getTitle() %></marquee></center>
			<center><img class="thumbnail" src="<%= entry.getLink("thumbnail").getHref() %>" /></center>
		</div>
	</body>	
</html>