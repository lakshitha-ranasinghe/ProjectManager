<%-- 
    Document   : releaseReport
    Created on : Jun 11, 2016, 10:07:49 AM
    Author     : Lakshitha
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--<script type="text/javascript" src="<c:url value='/external/js/release/releaseReport.js' />"></script>-->
<!DOCTYPE html>
<div class="breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-sm-4">
                <h1>
                    Release Report
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
                        Release Report
                    </li>
                </ol>
            </div>
        </div>
    </div>
</div>
<div class="component-bg">
    <div class="container">
        <div class="bs-docs-section">              
            <div class="col-lg-9 ">
                <c:forEach items="${releases}" var="release">
                    <div class="blog-item">
                        <div class="row">
                            <div class="col-lg-2 col-sm-2">
                                <div class="date-wrap">
                                    <span class="date">
                                        <fmt:formatDate value="${release.dateOfRelease}"  type="date"  pattern="d" />
                                    </span>
                                    <span class="month">
                                        <fmt:formatDate value="${release.dateOfRelease}"  type="date"  pattern="MMMM" />
                                    </span>
                                </div>

                            </div>
                            <div class="col-lg-10 col-sm-10">
                                <h4>
                                    <a href="#">
                                        <c:out value="${release.bugFixes}" /> Bug fixes and <c:out value="${release.tasks}" /> Tasks Released
                                    </a>
                                </h4>

                                <a href="processTaskReleaseReport?id=${release.id}">
                                    <button id="${release.id}" class="btn btn-primary generateBugReport">Task Report</button>
                                </a>
                                <a href="processBugReleaseReport?id=${release.id}">
                                    <button id="${release.id}" class="btn btn-primary generateBugReport">Bug Report</button>
                                </a>
                                <a href="processOverallReleaseReport?id=${release.id}">
                                    <button id="${release.id}" class="btn btn-primary generateBugReport">Release Report</button>
                                </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>