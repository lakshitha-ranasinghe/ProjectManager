<%-- 
    Document   : manageEmployee
    Created on : May 21, 2016, 10:20:04 AM
    Author     : Lakshitha
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="<c:url value='/external/js/employees/manageEmployee.js' />"></script>

<div class="breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-sm-4">
                <h1>
                    Employees
                </h1>
            </div>
            <div class="col-lg-8 col-sm-8">
                <ol class="breadcrumb pull-right">
                    <li>
                        <a href="#">
                            Home
                        </a>
                    </li>
                    <li class="active">
                        Manage Employee
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

        <div class="bs-docs-section">              
            <div class="mar-b-30">
                <table class="table table-hover" id="employeeTable">
                    <thead>
                        <tr>
                            <th>
                                Full Name
                            </th>
                            <th>
                                Email
                            </th>
                            <th>
                                Role
                            </th>
                            <th>
                                Username
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${employees}" var="employee" >
                            <tr id="tr${employee.id}">
                                <td>
                                    <c:out value="${employee.fullName}" />
                                </td>
                                <td>
                                    <c:out value="${employee.email}" />
                                </td>
                                <td>
                                    <c:out value="${employee.type}" />
                                </td>
                                <td>
                                    <c:out value="${employee.userName}" />
                                </td> 
                                <td>
                                    <button id="${employee.id}" class="btn btn-danger deleteButton">Delete</button>
                                </td>  
                            </tr>

                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

