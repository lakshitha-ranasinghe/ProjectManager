<%-- 
    Document   : customReport
    Created on : Nov 28, 2016, 12:06:57 AM
    Author     : Lakshitha
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="<c:url value='/external/js/employees/manageEmployee.js' />"></script>

<div class="breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-sm-4">
                <h1>
                    Customized Report
                </h1>
            </div>
            <div class="col-lg-8 col-sm-8">
                <ol class="breadcrumb pull-right">
                    <li>
                        <a href="#">
                            Report
                        </a>
                    </li>
                    <li class="active">
                        Customized Report
                    </li>
                </ol>
            </div>
        </div>
    </div>
</div>
<!--breadcrumbs end-->

<div class="component-bg container">
    <div class="bs-docs-section mar-b-30">
        <div class="bs-example">
            <p class="text-danger">
                **Developing not completed**
            </p>
            <form class="form-horizontal" role="form" method="POST" action="processCustomReport">
                <div class="form-group">
                    <label for="primaryMenu" class="col-sm-2 control-label">Title</label>
                    <div class="col-sm-3">
                        <form:select path="primaryMenu" multiple="false" class="form-control" name="primaryMenu">
                            <form:options items="${primaryMenu}" itemLabel="areaName" itemValue="areaName"/>
                        </form:select>   
                    </div>
                    <div class="col-sm-3">
                        <form:select path="secondaryMenu" multiple="false" class="form-control" name="secondaryMenu">
                            <form:options items="${secondaryMenu}" itemLabel="areaName" itemValue="areaName"/>
                        </form:select>   
                    </div>                  
                    <div class="col-sm-3">
                        <form:select path="terneryMenu" multiple="false" class="form-control" name="terneryMenu">
                            <form:options items="${terneryMenu}" itemLabel="areaName" itemValue="areaName"/>
                        </form:select>                          
                    </div>
                </div>
                <div class="form-group">                   
                    <div class="col-sm-10">
                        <button class="btn btn-primary" type="submit">Generate</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
