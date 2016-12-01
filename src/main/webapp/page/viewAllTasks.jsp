<%-- 
    Document   : viewAllTasks
    Created on : May 31, 2016, 9:16:59 PM
    Author     : Lakshitha
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="<c:url value='/external/js/tasks/viewAllTasks.js' />"></script>

<!DOCTYPE html>
<div class="breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-sm-4">
                <h1>
                    Tasks
                </h1>
            </div>
            <div class="col-lg-8 col-sm-8">
                <ol class="breadcrumb pull-right">
                    <li>
                        <a href="#">
                            Tasks
                        </a>
                    </li>
                    <li class="active">
                        View All Tasks
                    </li>
                </ol>
            </div>
        </div>
    </div>
</div>
<!--breadcrumbs end-->

<!--container start-->
<div class="component-bg">
    <div class="container">   
        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4>Task Info</h4>
                    </div>
                    <div class="modal-body" id="mbody">
                        <div class="container-fluid bd-example-row">
                            <div class="row">
                                <div class="col-md-4"><h5>Assigned Date</h5></div>  
                                <div class="col-md-4"><h5 id="assignedDate"></h5></div>  
                            </div>                           
                            <div class="row">
                                <div class="col-md-4"><h5>Completed Date</h5></div>  
                                <div class="col-md-4"><h5 id="resolvedDate"></h5></div>  
                            </div>
                            <div class="row">
                                <div class="col-md-4"><h5>Released Date</h5></div>  
                                <div class="col-md-4"><h5 id="releasedDate"></h5></div>  
                            </div>                          
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="reset" class="btn btn-info" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div> 
        <div class="col-lg-6">
            <form action="search">
                <div class="input-group margin">
                    <div class="input-group-btn">
                        <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">Filter <span class="fa fa-caret-down"></span></button>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value="/task/assignedOnly" />">Assigned Only</a></li>
                            <li><a href="<c:url value="/task/completedOnly" />">Completed Only</li>
                            <li><a href="<c:url value="/task/releasedOnly" />">Released Only</a></li>
                            <li><a href="<c:url value="/task/onlyMe" />">Only Me</a></li>
                            <li><a href="<c:url value="/task/viewAll" />">All</a></li>
                        </ul>
                    </div>
                    <input type="text" name="keyword" class="form-control">
                    <span class="input-group-btn">
                        <button class="btn btn-info" type="submit">Go!</button>
                    </span>
                </div>
            </form>
        </div>
        <div class="bs-docs-section">              
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>
                                #
                            </th>
                            <th>
                                Title
                            </th>
                            <th>
                                Description
                            </th>
                            <th>
                                Assigned
                            </th>                          
                            <th>
                                Action
                            </th>                            
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${tasks}" var="task" >         
                            <tr id="tr${task.id}">
                                <td>
                                    <span class="dropcap"> <c:out value="${task.id}" /> </span>
                                </td>
                                <td>
                                    <c:out value="${task.title}" />
                                </td>
                                <td>
                                    <c:out value="${task.description}" />
                                </td>
                                <td>
                                    <c:out value="${task.assignedTo.firstName}" />
                                </td>                               
                                <td>
                                    <c:choose>
                                        <c:when test="${task.status == 'Assigned'}"> 
                                            <div class="btn-group">
                                                <button id="manage${task.id}" type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    Manage <span class="caret"></span>
                                                </button>
                                                <ul class="dropdown-menu">                                                  
                                                    <li><a id="${task.id}" class="info" href="#info">Info</a></li>    
                                                    <li role="separator" class="divider"></li>
                                                    <li><a id="${task.id}" class="complete" href="#complete">Complete</a></li>       
                                                    <li><a id="${task.id}" class="release" href="#release">Release</a></li>     
                                                    <li role="separator" class="divider"></li>
                                                    <li><a id="${task.id}" class="delete" href="#delete">Delete</a></li>
                                                </ul>
                                            </div>                                        
                                        </c:when>
                                        <c:when test="${task.status == 'Completed'}"> 
                                            <div class="btn-group">
                                                <button id="manage${task.id}" type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    Manage <span class="caret"></span>
                                                </button>
                                                <ul class="dropdown-menu">      
                                                    <li><a id="${task.id}" class="info" href="#info">Info</a></li>
                                                    <li><a id="${task.id}" class="delete" href="#delete">Delete</a></li>                                                   
                                                    <li role="separator" class="divider"></li>                                                   
                                                    <li><a id="${task.id}" class="release" href="#release">Release</a></li>       
                                                </ul>
                                            </div>    
                                        </c:when>
                                        <c:when test="${task.status == 'Released'}"> 
                                            <div class="btn-group">
                                                <button id="manage${task.id}" type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    Manage <span class="caret"></span>
                                                </button>
                                                <ul class="dropdown-menu">        
                                                    <li><a id="${task.id}" class="info" href="#info">Info</a></li>
                                                    <li><a id="${task.id}" class="delete" href="#delete">Delete</a></li>                                                   
                                                </ul>
                                            </div>    
                                        </c:when>
                                        <c:when test="${task.status == 'Live_Released'}"> 
                                            <div class="btn-group">
                                                <button id="manage${task.id}" type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    Manage <span class="caret"></span>
                                                </button>
                                                <ul class="dropdown-menu">        
                                                    <li><a id="${task.id}" class="info" href="#info">Info</a></li>
                                                    <li><a id="${task.id}" class="delete" href="#delete">Delete</a></li>                                                   
                                                </ul>
                                            </div>    
                                        </c:when>
                                    </c:choose>
                                </td> 
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>