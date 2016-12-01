<%-- 
    Document   : newBug
    Created on : May 25, 2016, 9:05:51 PM
    Author     : Lakshitha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                        Add Bugs
                    </li>
                </ol>
            </div>
        </div>
    </div>
</div>

<div class="component-bg container">
    <div class="bs-docs-section mar-b-30">
        <div class="bs-example">
            <form class="form-horizontal" role="form" method="POST" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="primaryMenu" class="col-sm-2 control-label">Title</label>
                    <div class="col-sm-3">
                        <form:select path="primaryMenu" items="${primaryMenu}" class="form-control" name="title"/>
                    </div>
                    <div class="col-sm-3">
                        <form:select path="secondaryMenu" items="${secondaryMenu}" class="form-control" name="title"/>
                    </div>                  
                    <div class="col-sm-3">
                        <form:select path="terneryMenu" items="${terneryMenu}" class="form-control" name="title"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="description" class="col-sm-2 control-label">Description</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" rows="6" name="description"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label for="assignedTo" class="col-sm-2 control-label">Assigned To</label>
                    <div class="col-sm-3">                      
                        <form:select path="assignedTo" multiple="false" class="form-control" name="assignedEmployee">
                            <form:options items="${assignedTo}" itemLabel="fullName" itemValue="id"/>
                        </form:select>                      
                    </div>
                </div>
                <div class="form-group">
                    <label for="page" class="col-sm-2 control-label">Page</label>
                    <div class="col-sm-3">                      
                        <form:select path="pagesToAssign" multiple="false" class="form-control" name="pageId">
                            <form:options items="${pagesToAssign}" itemLabel="pageName" itemValue="id"/>
                        </form:select>                      
                    </div>
                </div>
                <div class="form-group">         
                    <div class="col-sm-10">
                        <input type="file" name="bugImage" id="bugImage" />
                    </div>
                </div>
                <div class="form-group">                   
                    <div class="col-sm-10">
                        <button class="btn btn-lg btn-login btn-primary" type="submit">Add</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>