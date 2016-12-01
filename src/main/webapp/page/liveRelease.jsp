<%-- 
    Document   : LiveRelease
    Created on : Jun 13, 2016, 12:32:39 PM
    Author     : shehan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/external/css/build.css" rel="stylesheet">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="<c:url value='/external/js/release/liveRelease.js' />"></script>
<!DOCTYPE html>
<div class="breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-sm-4">
                <h1>Release</h1>
            </div>
            <div class="col-lg-8 col-sm-8">
                <ol class="breadcrumb pull-right">
                    <li class="active">Live Release</li>
                </ol>
            </div>
        </div>
    </div>
</div>
<div class="component-bg">
    <div class="container">
        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4>Release Info</h4>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid bd-example-row"  id="modalContent">
                            
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="reset" class="btn btn-info" id="modalClose" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div> 
        <div class="form-group">                   
            <div class="col-sm-12">
                <button id="releaseButton" class="btn btn-lg btn-login btn-info" type="submit">Release</button>
            </div>
        </div>
        <hr>
        <div class="checkbox checkbox-success">
            <input id="allBugs" class="styled" type="checkbox">
            <label for="allBugs">
                <strong>Bugs</strong>
            </label>
        </div>   
        <c:forEach items="${bugs}" var="bug" >   
            <div class="checkbox checkbox-primary">
                <input id="${bug.id}" class="styledBug" type="checkbox">
                <label for="${bug.id}">
                    <span class="text-info">
                        <c:out value="${bug.id}" />
                    </span>-
                    <span class="text-success">
                        <c:out value="${bug.assignedTo.fullName}" />
                    </span>
                    <c:out value="${bug.description}" />                             
                </label>
            </div>
        </c:forEach>
        <br>
        <div class="checkbox checkbox-success">
            <input id="allTasks" class="styled" type="checkbox">
            <label for="allTasks">
                <strong>Tasks</strong>
            </label>
        </div> 
        <c:forEach items="${tasks}" var="task" >                 
            <div class="checkbox checkbox-primary">
                <input id="${task.id}" class="styledTask" type="checkbox">
                <label for="${task.id}">
                    <span class="text-info">
                        <c:out value="${task.id}" />
                    </span>-
                    <span class="text-success">
                        <c:out value="${task.assignedTo.fullName}" />
                    </span>
                    <c:out value="${task.description}" />                             
                </label>
            </div>
        </c:forEach>
    </div>
</div>