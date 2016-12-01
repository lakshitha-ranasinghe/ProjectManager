<%-- 
    Document   : addTask
    Created on : May 30, 2016, 4:20:33 PM
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
                        Add Tasks
                    </li>
                </ol>
            </div>
        </div>
    </div>
</div>

<div class="component-bg container">
    <div class="bs-docs-section mar-b-30">
        <div class="bs-example">
            <form class="form-horizontal" role="form" method="POST" >
                <div class="form-group">
                    <label for="title" class="col-sm-2 control-label">Title</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="title" name="title">
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
                    <div class="col-sm-10">
                        <button class="btn btn-lg btn-login btn-primary" type="submit">Add</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
