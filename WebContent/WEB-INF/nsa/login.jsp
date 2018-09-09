<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>NSA</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="./NSA/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="./NSA/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="./NSA/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="./NSA/css/AdminLTE.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="./NSA/css/blue.css">
  <!-- Google Font -->
  <link rel="stylesheet" href="./NSA/css/google-font.css">
  <script src="./NSA/js/sweetalert.min.js"></script>
  <script src="./NSA/js/alertify.js"></script>

</head>
<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <a ><img src="./NSA/img/c.png" width="300px" length ="88px">
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
    <p class="login-box-msg">Sign in to start your session</p>

    <form method="post" onsubmit="submiting();return false; " name="loginForm">
      <div class="form-group has-feedback">
        <input id='username' type="text" class="form-control" name="username" placeholder="uname">
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input id='password' type="password" class="form-control" name="password" placeholder="Password">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="row">
        <div class="col-xs-8">
          <div class="checkbox icheck">
            <label>
              <input  id="checkb" type="checkbox"> Remember Me
            </label>
          </div>
        </div>
        <!-- /.col -->
        <div class="col-xs-4">
          <button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
        </div>
        <!-- /.col -->
      </div>
    </form>


  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery 3 -->
<script src="./NSA/js/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="./NSA/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="./NSA/js/icheck.min.js"></script>
<script>
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' /* optional */
    });
  });
</script>




  <script type="text/javascript">
  
    var submiting = function (){
    data = {'username':$('#username').val()
           ,'password':$('#password').val()};
    $.post("loginPage",data,function(data){
      
      if(data=="success"){



        if ($('#checkb').is(':checked')) {
            // save username and password
            localStorage.username = $('#username').val();
            localStorage.pass = $('#password').val();
            localStorage.chkbox = $('#checkb').val();
        } else {
            localStorage.username = '';
            localStorage.pass = '';
            localStorage.chkbox = '';
        }


         location.reload();
         location.href ='/';
      }
      else{
         
        swal({
        text: data,
        icon: "warning",
         dangerMode: true});
      }
      return false;

    });
  }


        if (localStorage.chkbox && localStorage.chkbox != '') {
            $('#checkb').attr('checked', 'checked');
            $('#username').val(localStorage.username);
            $('#password').val(localStorage.pass);
        } else {
            $('#checkb').removeAttr('checked');
            $('#username').val('');
            $('#password').val('');
        }





  </script>
</body>
</html>
