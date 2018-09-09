<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>NSA</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="./NSA/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="./NSA/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="./NSA/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="./NSA/css/AdminLTE.min.css">

  <link rel="stylesheet" href="./NSA/css/skin-blue.min.css">
    
  <link rel="stylesheet" type="text/css" href="./NSA/css/custom.css">

  <div id="data-holder" data=""></div>

</head>


<body class="hold-transition skin-blue sidebar-collapse sidebar-mini" style="height: auto; min-height: 100%;">
<div class="wrapper" style="height: auto; min-height: 100%;">


  <!-- Main Header -->
  <header class="main-header">

    <!-- Logo -->
    <a href="/" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>N</b>SA</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>N</b>etwork<b>S</b>erver<b>A</b>dmin</span>
    </a>

    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->
          <li class="dropdown messages-menu">
            <!-- Menu toggle button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-envelope-o"></i>
              <span class="label label-success"></span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 0 messages</li>
              <li>
                <!-- inner menu: contains the messages -->
                <ul class="menu">
                  <li><!-- start message -->
                    <a href="#">
                      <div class="pull-left">
                   
                      </div>
                      <!-- Message title and timestamp -->
                      <h4>
                        <small><i class="fa fa-clock-o"></i></small>
                      </h4>
                      <!-- The message -->
                    </a>
                  </li>
                  <!-- end message -->
                </ul>
                <!-- /.menu -->
              </li>
              <li class="footer"><a href="mailbox.html">See All Messages</a></li>
            </ul>
          </li>
          <!-- /.messages-menu -->

          <!-- Notifications Menu -->
          <li class="dropdown notifications-menu">
            <!-- Menu toggle button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-bell-o"></i>
              <span class="label label-warning"></span>
            </a>
            <ul class="dropdown-menu">
              <li class="header"></li>
              <li>
                <!-- Inner Menu: contains the notifications -->
                <ul class="menu">
                  <li><!-- start notification -->
                    <a href="#">
                      <i class="fa fa-users text-aqua"></i> 
                    </a>
                  </li>
                  <!-- end notification -->
                </ul>
              </li>
              <li class="footer"><a href="#"></a></li>
            </ul>
          </li>
          <!-- Tasks Menu -->
          <li class="dropdown tasks-menu">
            <!-- Menu Toggle Button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-flag-o"></i>
              <span class="label label-danger"></span>
            </a>
            <ul class="dropdown-menu">
              <li class="header"></li>
              <li>
                <!-- Inner menu: contains the tasks -->
                <ul class="menu">
                  <li><!-- Task item -->
                    <a href="#">
                      <!-- Task title and progress text -->
                      <h3>
                        
                     
                      </h3>
                      <!-- The progress bar -->
                      <div class="progress xs">
                        <!-- Change the css width attribute to simulate progress -->
                        <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar"
                             aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">20% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  <!-- end task item -->
                </ul>
              </li>
              <li class="footer">
                <a href="#">View all tasks</a>
              </li>
            </ul>
          </li>
          <!-- User Account Menu -->
          <li class="dropdown user user-menu">
            <!-- Menu Toggle Button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <!-- The user image in the navbar-->
              <img src="./NSA/img/boxed-bg.jpg" class="user-image" alt="User Image">
              <!-- hidden-xs hides the username on small devices so only the image appears. -->
              <span class="hidden-xs">admin name</span>
            </a>
            <ul class="dropdown-menu">
              <!-- The user image in the menu -->
              <li class="user-header">
                <img src="./NSA/img/boxed-bg.jpg" class="img-circle" alt="User Image">

                <p>
                 admin name / admin title
                  <small>admin info</small>
                </p>
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">Profile</a>
                </div>
                <div class="pull-right">
                  <a href="logout" class="btn btn-default btn-flat">Sign out</a>
                </div>
              </li>
            </ul>
          </li>
          <!-- Control Sidebar Toggle Button -->
          <li>
            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

      <!-- Sidebar user panel (optional) -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="./NSA/img/boxed-bg.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>admin name</p>
          <!-- Status -->
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>

      <!-- search form (Optional) -->
      <form  method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search...">
          <span class="input-group-btn">
              <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
              </button>
            </span>
        </div>
      </form>
      <!-- /.search form -->

      <!-- Sidebar Menu -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">Menu</li>
        <!-- Optionally, you can add icons to the links -->
        <li class="active"><a href="#"><i class="fa fa-link"></i> <span>Link</span></a></li>
        <li><a href="#"><i class="fa fa-link"></i> <span>Another Link</span></a></li>
        <li class="treeview">
          <a href="#"><i class="fa fa-link"></i> <span>Multilevel</span>
            <span class="pull-right-container">
                <i class="fa fa-angle-left pull-right"></i>
              </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="#">Link in level 2</a></li>
            <li><a href="#">Link in level 2</a></li>
          </ul>
        </li>
      </ul>
      <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
    
    </section>

    <!-- Main content -->
    <section class="content container-fluid">
	<!--computers -->
  <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box" id="computers">
            <span class="info-box-icon bg-green" data-toggle="collapse" data-target="#demo"data-parent="#accordion2" ><i class="ion-android-laptop"></i></span>
			       <div>
                <div class="info-box-content">
              <span class="info-box-text">computers</span>
              <span id='val_of_computers' class="info-box-number">0</span>
            </div>
           </div>

  </div>
            <!--body-->
			<div id="demo" class="accordion-body collapse ">
        <table id="tmplate_for_computer">


			   	</table>
          <div>
          <button type="button" class="btn btn-space button2" data-toggle="modal" data-target="#modal-addpc">add device</button>
			   </div>
        </div> 
        </div>  
		<!-- phones -->
		  <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box" id="phones">
            <span class="info-box-icon bg-aqua"  data-toggle="collapse" data-target="#demo1" data-parent="#accordion2"><i class="ion-iphone"></i></span>
             <div class="info-box-content">
              <span class="info-box-text">phones</span>
              <span id='val_of_phones' class="info-box-number">0</span>
         </div>  
              </div>
            <div id="demo1" class="accordion-body collapse ">
                  <table id="tmplate_for_phone">  
      
      			  </table>
              <div>
         </div>
            </div>
            <!-- /.modal-content -->
      
          <!-- /.modal-dialog -->
        </div>
        <!-- /.modal -->
        
		<!-- printers -->
		  <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box" id="printers">
            <span class="info-box-icon bg-yellow" data-toggle="collapse" data-target="#demo2" data-parent="#accordion2"><i class="ion-printer"></i></span>
					<div class="info-box-content">
              <span class="info-box-text">printers</span>
              <span id='val_of_printers' class="info-box-number">0</span>
            </div>
			</div>
              <div id="demo2" class="accordion-body collapse ">
               <table id="tmplate_for_printer">  
			   
			  </table>
        <div>
         </div>
              </div>
        </div>
		<!-- other model -->
		    <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box" id="others">
            <span class="info-box-icon bg-red" data-toggle="collapse" data-target="#demo3" data-parent="#accordion2"><i class="ion-android-add-circle"></i></span> 
              <div class="info-box-content">
              <span class="info-box-text">other</span>
              <span id='val_of_others' class="info-box-number">0</span>
            </div>
			  </div>
              <div id="demo3" class="accordion-body collapse ">
                <p>others don't know</p> 
          </div>
          <!-- /.info-box -->
        </div>
		 </section>
<div>
</div>

<!-- first modal of pc1-->
		<div class="modal fade" id="modal-computer">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                   <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="mtitle"></h4>
              </div>
			   <div class="modal-body">
			 <p id="info-box-computer"></p>
			  </div>
			 <div class="modal-footer">
                <table  style="width:50% ;" align="center"  >
			  <tr >
			  <td style= "margin:-10%"><i  data-toggle="modal" data-target="#modal-pcsendmsg"><button  type="button" class="btn btn-space  ">Send messages</button></td>
			   <td><i  data-toggle="modal" data-target="#modal-pcsendfile"><button type="button" class="btn  btn-space button2">Send files</button></i></td>
			   <td><button  onclick="send_act();" type="button" class="btn  btn-space button3">lock</button></td>
			   	<td><button onclick="send_act2();" type="button" class="btn  btn-space button3">restart</button></td>

			   </tr>
			   </table>
              </div>
			</div>
			</div>
			</div> 
			<!-- end of first modal of pc1-->
			<!-- start first  modal of phone1-->
				<div class="modal fade" id="modal-defaultph1">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                   <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">phone1</h4>
              </div>
			   <div class="modal-body">
			 <p> phone1|user name|user id|user ip: 189.136.10.1|phone mark:samsung | phone os:android/iphone/microsoft</p>
			  </div>
			 <div class="modal-footer">
             	  <table  style="width:25%" align="center"  >
			  <tr>
			  <td> <i  data-toggle="modal" data-target="#modal-pcsendmsg"><button type="button" class="btn btn-space ">Send messages</button></i></td>
			   <td><i  data-toggle="modal" data-target="#modal-pcsendfile"><button type="button" class="btn btn-space button2">Send files</button></i></td>
			   </tr>
			   </table>
              </div>
			</div>
			</div>
			</div> 
						<!-- end first  modal of phone1-->
						<!--  first  modal of printer -->
		
	<div class="modal fade" id="modal-defaultprinter">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                   <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">printer1 status</h4>
              </div>
			   <div class="modal-body">
			 <p> printer1|printer1 id||printer mark:epson | phone status :active/inactive</p>
			  </div>
			 <div class="modal-footer">
               <table  style="width:25%" align="center"  >
			  <tr>
			  <td> <button type="button" class="btn  btn-space ">print</button></td>
			   <td><button type="button" class="btn  btn-space button2 ">printer status</button></td>
			   </tr>
			   </table>
              </div>
			</div>
			</div>
			</div>
									<!-- end first  modal of pr-->

			<!--send file pc modal -->
			
			<div class="modal fade" id="modal-pcsendfile">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                   <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">send file</h4><input type="hidden" name="host" id='host_mach'>
              </div>
			   <div class="modal-body">
			 
			  </div>
			 <div class="modal-footer">
               <button align="center" type="button" class="btn btn-space button2"><input type="file" id="myfile" name="myfile" value=""></button>
                <button type="button" class="btn btn-space" onclick="submit_file();">send</button>
				
              </div>
			</div>
			</div>
			</div>
				<!--send msg pc modal -->
			<div class="modal fade" id="modal-pcsendmsg">
			<div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                   <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">send message</h4>
              </div>
			   <div class="modal-body">
			 <textarea id='msg_body' rows="4" cols="85">
			</textarea>
			  </div>
			 <div class="modal-footer">
                <button onclick="send_msg(this);" class="btn btn-space">send</button>
              </div>
			</div>
			</div>
			</div>
              <!--add device modal -->

      <div class="modal fade" id="modal-addpc">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                   <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">add device</h4>
              </div>
         <div class="modal-body form-group">
          <form method="post" >
             <table>
      <tr>
        <tr>name :</tr><tr><input type="text" name="name" class="form-control input-sm"></tr>
      </tr> 
      <tr>
        <tr>host: </tr><tr><input type="text" name="host" class="form-control input-sm"></tr>
      </tr> 
      <tr>
        <tr>port :</tr><tr><input type="text" name="port" class="form-control input-sm"></tr>
      </tr> 
      <input type="hidden" name="type" value="computer" >
        </table>
        </div>
       <div class="modal-footer">
               <button align="center" type="submit" class="btn btn-space button2">admit</button>
        
              </div>
          </form>
      </div>
      </div>
      </div>
			<!-- ussfull stuffffffff-->

		 <div class="row">
		   
        <div class="col-lg-3 col-xs-6" >
          <!-- small box -->
          <div class="small-box bg-aqua">
            <div class="inner">
              <h3 id='val_of_msgs'>0</h3>

              <p>New Messages</p>
            </div>
            <div class="icon">
              <i class="ion-android-mail" ></i>
            </div>
            <a href="mailbox" class="small-box-footer">
              <i class="fa fa-arrow-circle-right"></i>
            </a>
          </div>
        </div>
        <!-----col -->
                <!-- ./col -->
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-yellow">
            <div class="inner">
      <div>
              <h3>0</h3>
      </div>
              <p>callender</p>
            </div>
            <div class="icon">
              <i class="ion-calendar"></i>
            </div>
            <a href="calendar.html" class="small-box-footer">
            <i class="fa fa-arrow-circle-right"></i>
            </a>
          </div>
        </div>

        <!-- ./col -->
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-green">
            <div class="inner">
              <h3 id='val_of_donlines'>0<sup style="font-size: 20px"></sup></h3>

              <p>Devices Online</p>
            </div>
            <div class="icon">
              <i class="ion-android-wifi"></i>
            </div>
          </div>
        </div>

        <!-- ./col -->
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-red">
            <div class="inner">
              <h3>0</h3>
              <p>Charts</p>
            </div>
            <div class="icon">
              <i class="ion ion-pie-graph"></i>
            </div>
          </div>
        </div>
        <!-- ./col -->
      </div>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Main Footer -->
  <footer class="main-footer">
    <!-- To the right -->
    <div class="pull-right hidden-xs">
    
    </div>
    <!-- Default to the left -->
   
  </footer>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Create the tabs -->
    <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
      <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
      <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
    </ul>
    <!-- Tab panes -->
    <div class="tab-content">
      <!-- Home tab content -->
      <div class="tab-pane active" id="control-sidebar-home-tab">
        <h3 class="control-sidebar-heading">Recent Activity</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript:;">
              <i class=></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">news news news</h4>

                <p></p>
              </div>
            </a>
          </li>
        </ul>
        <!-- /.control-sidebar-menu -->

        <h3 class="control-sidebar-heading">Tasks Progress</h3>
        <ul class="control-sidebar-menu">
      </div>
      <!-- /.tab-pane -->
      <!-- Stats tab content -->
      <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
      <!-- /.tab-pane -->
      <!-- Settings tab content -->
      <div class="tab-pane" id="control-sidebar-settings-tab">
        <form method="post">
          <h3 class="control-sidebar-heading">General Settings</h3>

          <div class="form-group">
            <label class="control-sidebar-subheading">
              Report panel usage
              <input type="checkbox" class="pull-right" checked>
            </label>

            <p>
              Some information about this general settings option
            </p>
          </div>
          <!-- /.form-group -->
        </form>
      </div>
      <!-- /.tab-pane -->
    </div>
  </aside>
  <!-- /.control-sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 3 -->
<script src="./NSA/js/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="./NSA/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="./NSA/js/adminlte.min.js"></script>
<!-- Custom App
<script src="./NSA/js/update.js"></script>
 -->
 <script src="./NSA/js ws/SockJs.js"></script>
 <script src="./NSA/js ws/stomp.js"></script>

<script src="./NSA/js/sweetalert.min.js"></script>
<script src="./NSA/js/alertify.js"></script>

<script src="./NSA/templates/template.nsa.js"></script>
<script src="./NSA/js2ws2/home.ws.js">alert("hi");</script>

<script type="text/javascript">
  submit_file = function(){
    var formData = new FormData();
    formData.append('myfile', $('#myfile')[0].files[0]);
    formData.append('host', $('#host_mach').val());

$.ajax({
       url : '/file',
       type : 'POST',
       enctype: 'multipart/form-data',//for sending multiple files
       data : formData,
       processData: false,  // tell jQuery not to process the data
       contentType: false,  // tell jQuery not to set contentType
       success : function(data) {
           if (data=="success")
        	swal('success');
           else
        	   swal('failed !');
       }
});
  }

 show_data = function(t){
   $("#info-box-"+$(t).attr('type')).html();
   $("#info-box-"+$(t).attr('type')).html(" host : "+ $(t).attr('host')+" status : "+ $(t).attr('status'));
   $('#host_mach').val($(t).attr('host'));
   $('#mtitle').html($(t).attr('name'));

};
     
</script>



</body>
</html>