<%-- 
    Document   : newEmployee
    Created on : May 21, 2016, 1:01:21 AM
    Author     : Lakshitha
--%>

<div class="breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-sm-4">
                <h1>New Employee</h1>
            </div>
            <div class="col-lg-8 col-sm-8">
                <ol class="breadcrumb pull-right">
                    <li><a href="#">Employee</a></li>
                    <li class="active">New Employee</li>
                </ol>
            </div>
        </div>
    </div>
</div>
<!--breadcrumbs end-->

<!--container start-->
<!--<div class="bs-docs-section mar-b-30">
    <div class="container">
        <div class="bs-docs-section mar-b-30">
            <form role="form" method="POST">     
                <div class="form-group">
                    <label for="fullName">Full Name</label>
                    <input type="text" id="fullName" name="fullName" class="form-control" >
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="text" id="email" name="email" class="form-control">
                </div>
                <div class="radio">
                    <label>
                        <input type="radio" name="type" id="optionsRadios1" value="Developer" checked>
                        Developer
                    </label>
                </div>
                <div class="radio">
                    <label>
                        <input type="radio" name="type" id="optionsRadios1" value="Database" >
                        Database
                    </label>
                </div>
                <div class="radio">
                    <label>
                        <input type="radio" name="type" id="optionsRadios1" value="QA" >
                        QA
                    </label>
                </div>
                <div class="radio">
                    <label>
                        <input type="radio" name="type" id="optionsRadios1" value="Support" >
                        Support
                    </label>
                </div>
                <div class="form-group">
                    <label for="userName">Username</label>
                    <input type="text" id="userName" name="userName" class="form-control">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" class="form-control">
                </div>
                <div class="form-group">
                    <label for="confirmPassword">Confirm Password</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" class="form-control">
                </div>               
                <button type="submit" class="btn btn-primary btn-lg">Submit</button>
            </form>
        </div> -->
<form class="form-signin wow fadeInUp" method="POST">
    <h2 class="form-signin-heading">Employee</h2>
    <div class="login-wrap">
        <p>Enter personal details</p>
        <input type="text" class="form-control" placeholder="Full Name" name="fullName" autofocus="">
        <input type="text" class="form-control" placeholder="Email" name="email" autofocus="">
        <div class="radios">
            <label class="label_radio col-lg-6 col-sm-6" for="radio">
                <input name="type" id="radio-01" value="Developer" type="radio" checked="checked"> Developer
            </label>
            <label class="label_radio col-lg-6 col-sm-6" for="radio">
                <input name="type" id="radio-02" value="Database" type="radio"> Database
            </label>
            <label class="label_radio col-lg-6 col-sm-6" for="radio">
                <input name="type" id="radio-01" value="QA" type="radio" > QA
            </label>
            <label class="label_radio col-lg-6 col-sm-6" for="radio">
                <input name="type" id="radio-01" value="Support" type="radio"> Support
            </label>
        </div>

        <br/><br/><br/><br/>
        <p> Enter account details below</p>

        <input type="text" class="form-control" placeholder="User Name" name="userName" autofocus="">
        <input type="password" class="form-control" name="password" placeholder="Password">
        <input type="password" class="form-control" placeholder="Re-type Password">
        <button class="btn btn-lg btn-login btn-block" type="submit">Submit</button>
    </div>
</form>
    </div>
</div>