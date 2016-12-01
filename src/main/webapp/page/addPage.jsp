<%-- 
    Document   : addPage
    Created on : Oct 21, 2016, 7:21:55 AM
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
                    Pages
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
                        Add Pages
                    </li>
                </ol>
            </div>
        </div>
    </div>
</div>

<div class="component-bg container">
    <div class="bs-docs-section mar-b-30">
        <div class="bs-example">
            <form class="form-horizontal" role="form" method="POST">
                <div class="form-group">
                    <label for="pageName" class="col-sm-1 control-label">Name</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="name" name="pageName">
                    </div>
                    <div class="col-sm-2">
                        <button class="btn btn btn-login btn-primary" type="submit">Add</button>
                    </div>
                </div>               
            </form>
        </div>
    </div>
</div>
