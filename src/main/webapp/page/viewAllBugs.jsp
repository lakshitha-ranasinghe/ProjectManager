<%-- 
    Document   : viewAllBugs
    Created on : May 28, 2016, 2:12:17 AM
    Author     : Lakshitha
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="<c:url value='/external/js/bugs/viewAllBugs.js' />"></script>

<!DOCTYPE html>
<div class="breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-sm-4">
                <h1>
                    Bugs
                </h1>
            </div>
            <div class="col-lg-8 col-sm-8">
                <ol class="breadcrumb pull-right">
                    <li>
                        <a href="#">
                            Bugs
                        </a>
                    </li>
                    <li class="active">
                        View All Bugs
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
                        <h4>Bug Info</h4>
                    </div>
                    <div class="modal-body" id="mbody">
                        <div class="container-fluid bd-example-row">
                            <div class="row">
                                <div class="col-md-4"><h5>Reported By</h5></div>  
                                <div class="col-md-4"><h5 id="reportedBy"></h5></div>  
                            </div>                           
                            <div class="row">
                                <div class="col-md-4"><h5>Assigned Date</h5></div>  
                                <div class="col-md-4"><h5 id="assignedDate"></h5></div>  
                            </div>
                            <div class="row">
                                <div class="col-md-4"><h5>Resolved Date</h5></div>  
                                <div class="col-md-4"><h5 id="resolvedDate"></h5></div>  
                            </div>
                            <div class="row">
                                <div class="col-md-4"><h5>Released Date</h5></div>  
                                <div class="col-md-4"><h5 id="releasedDate"></h5></div>  
                            </div>
                            <div class="row">
                                <div class="col-md-4"><h5>Closed Date</h5></div>  
                                <div class="col-md-4"><h5 id="closedDate"></h5></div>  
                            </div>
                            <div id="imageRow" class="row">
                            </div>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="reset" class="btn btn-info" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <div id="myImageModal" class="modal">
            <span id="imageClose" class="close">x</span> 
            <img class="imageModal-content" id="img01">
        </div>

        <div class="col-lg-6">
            <form action="search">
                <div class="input-group margin">
                    <div class="input-group-btn">
                        <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">Status <span class="fa fa-caret-down"></span></button>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value="/bug/assignedOnly" />">Assigned</a></li>
                            <li><a href="<c:url value="/bug/resolvedOnly" />">Resolved</li>
                            <li><a href="<c:url value="/bug/releasedOnly" />">Released</a></li>          
                            <li><a href="<c:url value="/bug/closedOnly" />">Closed</a></li> 
                            <li><a href="<c:url value="/bug/viewAll" />">All</a></li> 
                        </ul>
                    </div>
                    <div class="input-group-btn">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">Assigned <span class="fa fa-caret-down"></span></button>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value="/bug/onlyMe" />">Only Me</a></li>
                            <li><a href="<c:url value="/bug/allUsers" />">All</a></li>
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
                    <!--                    <colgroup>
                                            <col class="col-xs-1">
                                            <col class="col-xs-7">
                                        </colgroup>-->
                    <thead>
                        <tr>
                            <th>
                                #
                            </th>
                            <th>
                                Title
                            </th>
                            <th>
                                Page
                            </th>
                            <th>
                                Description
                            </th>
                            <th>
                                Assigned
                            </th>
                            <th>
                                Status
                            </th>
                            <th>
                                Action
                            </th>                            
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${bugs}" var="bug" >         
                            <tr id="tr${bug.id}">
                                <td>
                                    <span class="dropcap"> <c:out value="${bug.id}" /> </span>
                                </td>
                                <td>
                                    <c:out value="${bug.title}" />
                                </td>
                                <td>
                                    <c:out value="${empty bug.page.pageName ? '-' : bug.page.pageName}" />
                                    <%--<c:out value="${bug.page.pageName}" />--%>
                                </td>
                                <td>
                                    <c:out value="${bug.description}" />
                                </td>
                                <td>
                                    <c:out value="${bug.assignedTo.firstName}" />
                                </td>  
                                <td id="status${bug.id}">
                                    <c:out value="${bug.status}" />
                                </td>  
                                <td>
                                    <c:choose>
                                        <c:when test="${bug.status == 'Assigned'}"> 
                                            <div class="btn-group">
                                                <button id="manage${bug.id}" type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    Manage <span class="caret"></span>
                                                </button>
                                                <ul class="dropdown-menu">
                                                    <li><a id="${bug.id}" class="info" href="#info">Info</a></li>                                      
                                                    <li role="separator" class="divider"></li>
                                                    <li><a id="${bug.id}" class="resolve" href="#resolve">Resolve</a></li>
                                                    <li><a id="${bug.id}" class="release" href="#release">Release</a></li>
                                                    <li><a id="${bug.id}" class="closeIt" href="#close">Close</a></li>
                                                    <li><a id="${bug.id}" class="invalid" href="#invalid">Invalid</a></li>
                                                    <li><a id="${bug.id}" class="assign" href="#assign">Assign</a></li>
                                                    <li role="separator" class="divider"></li>
                                                    <li><a id="${bug.id}" class="delete" href="#delete">Delete</a></li>
                                                </ul>
                                            </div>                                        
                                        </c:when>
                                        <c:when test="${bug.status == 'Resolved'}"> 
                                            <div class="btn-group">
                                                <button id="manage${bug.id}" type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    Manage <span class="caret"></span>
                                                </button>
                                                <ul class="dropdown-menu">
                                                    <li><a id="${bug.id}" class="info" href="#info">Info</a></li>
                                                    <li><a id="${bug.id}" class="delete" href="#delete">Delete</a></li>
                                                    <li role="separator" class="divider"></li>
                                                    <li><a id="${bug.id}" class="resolve" href="#resolve">Resolve</a></li>
                                                    <li><a id="${bug.id}" class="release" href="#release">Release</a></li>
                                                    <li><a id="${bug.id}" class="closeIt" href="#close">Close</a></li>
                                                    <li><a id="${bug.id}" class="invalid" href="#invalid">Invalid</a></li>
                                                    <li><a id="${bug.id}" class="assign" href="#assign">Assign</a></li>
                                                </ul>
                                            </div>    
                                        </c:when>
                                        <c:when test="${bug.status == 'Released'}"> 
                                            <div class="btn-group">
                                                <button id="manage${bug.id}" type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    Manage <span class="caret"></span>
                                                </button>
                                                <ul class="dropdown-menu">
                                                    <li><a id="${bug.id}" class="info" href="#info">Info</a></li>
                                                    <li><a id="${bug.id}" class="delete" href="#delete">Delete</a></li>
                                                    <li role="separator" class="divider"></li>                                                  
                                                    <li><a id="${bug.id}" class="resolve" href="#resolve">Resolve</a></li>
                                                    <li><a id="${bug.id}" class="release" href="#release">Release</a></li>
                                                    <li><a id="${bug.id}" class="closeIt" href="#close">Close</a></li>
                                                    <li><a id="${bug.id}" class="invalid" href="#invalid">Invalid</a></li>
                                                    <li><a id="${bug.id}" class="assign" href="#assign">Assign</a></li>
                                                </ul>
                                            </div>    
                                        </c:when>
                                        <c:when test="${bug.status == 'Closed'}"> 
                                            <div class="btn-group">
                                                <button id="manage${bug.id}" type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    Manage <span class="caret"></span>
                                                </button>
                                                <ul class="dropdown-menu">
                                                    <li><a id="${bug.id}" class="info" href="#info">Info</a></li>
                                                    <li><a id="${bug.id}" class="delete" href="#delete">Delete</a></li>
                                                </ul>
                                            </div>    
                                        </c:when>
                                        <c:when test="${bug.status == 'Invalid'}"> 
                                            <div class="btn-group">
                                                <button id="manage${bug.id}" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    Manage <span class="caret"></span>
                                                </button>
                                                <ul class="dropdown-menu">
                                                    <li><a id="${bug.id}" class="info" href="#info">Info</a></li>
                                                    <li><a id="${bug.id}" class="delete" href="#delete">Delete</a></li>
                                                </ul>
                                            </div>    
                                        </c:when>
                                        <c:when test="${bug.status == 'Live_Released'}"> 
                                            <div class="btn-group">
                                                <button id="manage${bug.id}" type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    Manage <span class="caret"></span>
                                                </button>
                                                <ul class="dropdown-menu">
                                                    <li><a id="${bug.id}" class="info" href="#info">Info</a></li>
                                                    <li><a id="${bug.id}" class="delete" href="#delete">Delete</a></li>
                                                    <li role="separator" class="divider"></li>
                                                    <li><a id="${bug.id}" class="resolve" href="#resolve">Resolve</a></li>
                                                    <li><a id="${bug.id}" class="release" href="#release">Release</a></li>
                                                    <li><a id="${bug.id}" class="closeIt" href="#close">Close</a></li>
                                                    <li><a id="${bug.id}" class="invalid" href="#invalid">Invalid</a></li>
                                                    <li><a id="${bug.id}" class="assign" href="#assign">Assign</a></li>
                                                </ul>
                                            </div>    
                                        </c:when>
                                        <c:otherwise> <tr class="danger"> </c:otherwise>
                                </c:choose>      

                                </td> 

                            </tr>

                        </c:forEach>
                    </tbody>
                </table>
                <hr>
                <div class="wrapper">
                    <a href="viewAll">
                        <button type="reset" class="btn btn-lg bg-navy margin" data-dismiss="modal">Show All</button>
                    </a>
                </div>               
                <br><br>
            </div>
        </div>
    </div>
</div>