msg_tmp = function (g,sender,subj,msg,timestamp,from){
	return	"<tr>\
                   <td><div class='icheckbox_flat-blue' aria-checked='false' aria-disabled='false' style='position: relative;'><input type='checkbox' style='position: absolute; opacity: 0;'><ins class='iCheck-helper' style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;'></ins></div></td>\
                    <td class='mailbox-star'><a href='#'><i class='fa fa-star text-yellow'></i></a></td>\
                    <td class='mailbox-name'><a href='read-mail.html'>"+sender+"</a></td>\
                    <td class='mailbox-subject'><a onclick=readmsg('"+ from + "'," + g +"); href='#' ><b>"+subj+"</b>"+msg+
                    "</td>\
                    <td class='mailbox-attachment'></td>\
                    <td class='mailbox-date'>"+timestamp+"</td>\
                  </tr>";	
                  	}


container_tmp = function (type,color){

	return  "<div class='col-md-9'>\
          <div class='box box-"+color+"'>\
            <div class='box-header with-border'>\
              <h3 class='box-title'>"+type+"</h3>\
              <div class='box-tools pull-right'>\
                <div class='has-feedback'>\
                  <input type='text' class='form-control input-sm' placeholder='Search Mail'>\
                  <span class='glyphicon glyphicon-search form-control-feedback'></span>\
                </div>\
              </div>\
            </div>\
            <div class='box-body no-padding'>\
              <div class='mailbox-controls'>\
                <!-- Check all button -->\
                <button type='button' class='btn btn-default btn-sm checkbox-toggle'><i class='fa fa-square-o'></i>\
                </button>\
                <div class='btn-group'>\
                  <button type='button' class='btn btn-default btn-sm'><i class='fa fa-trash-o'></i></button>\
                  <button type='button' class='btn btn-default btn-sm'><i class='fa fa-reply'></i></button>\
                  <button type='button' class='btn btn-default btn-sm'><i class='fa fa-share'></i></button>\
                </div>\
                <button type='button' class='btn btn-default btn-sm'><i class='fa fa-refresh'></i></button>\
                <div class='pull-right'>\
                  1-50/200\
                  <div class='btn-group'>\
                    <button type='button' class='btn btn-default btn-sm'><i class='fa fa-chevron-left'></i></button>\
                    <button type='button' class='btn btn-default btn-sm'><i class='fa fa-chevron-right'></i></button>\
                  </div>\
                </div>\
              </div>\
              <div class='table-responsive mailbox-messages'>\
                <table class='table table-hover table-striped'>\
                  <tbody id='plt_msg'>\
                  </tbody>\
                </table>\
              </div>\
            </div>\
            <div class='box-footer no-padding'>\
              <div class='mailbox-controls'>\
                <button type='button' class='btn btn-default btn-sm checkbox-toggle'><i class='fa fa-square-o'></i>\
                </button>\
                <div class='btn-group'>\
                  <button type='button' class='btn btn-default btn-sm'><i class='fa fa-trash-o'></i></button>\
                  <button type='button' class='btn btn-default btn-sm'><i class='fa fa-reply'></i></button>\
                  <button type='button' class='btn btn-default btn-sm'><i class='fa fa-share'></i></button>\
                </div>\
                <button type='button' class='btn btn-default btn-sm'><i class='fa fa-refresh'></i></button>\
                <div class='pull-right'>\
                  1-50/200\
                  <div class='btn-group'>\
                    <button type='button' class='btn btn-default btn-sm'><i class='fa fa-chevron-left'></i></button>\
                    <button type='button' class='btn btn-default btn-sm'><i class='fa fa-chevron-right'></i></button>\
                  </div>\
                </div>\
              </div>\
            </div>\
          </div></div>";
          }



send_msg_tmp = function (){
	return "<div class='col-md-9'>\
          <div class='box box-primary'>\
            <div class='box-header with-/border'>\
              <h3 class='box-title'>Compose New Message</h3>\
            </div>\
            <div class='box-body'>\
              <div class='form-group'>\
                <input class='form-control' id='sendTo' placeholder='To:id'>\
              </div>\
              <div class='form-group'>\
                <input class='form-control' id='subjcT' placeholder='Subject:'>\
              </div>\
              <div class='form-group'>\
                    <textarea id='compose-textarea' class='form-control' style='height: 300px'>\
                    </textarea>\
              </div>\
              <div class='form-group'>\
                <div class='btn btn-default btn-file'>\
                  <i class='fa fa-paperclip'></i> Attachment\
                  <input type='file' name='attachment'>\
                </div>\
                <p class='help-block'>Max. 32MB</p>\
              </div>\
            </div>\
            <!-- /.box-body -->\
            <div class='box-footer'>\
              <div class='pull-right'>\
                <button type='button' class='btn btn-default' onclick='sendmsg(3);'><i class='fa fa-pencil'></i> Draft</button>\
                	<button type='submit' class='btn btn-primary' onclick='sendmsg(1);'><i class='fa fa-envelope-o'></i> Send</button>\
              </div>\
              <button type='reset' class='btn btn-default' onclick='discard();'><i class='fa fa-times'></i> Discard</button>\
            </div>\
          </div>\
        </div>";
}


container_read_tmp = function(msg,from,subj,color){

return "<div class='col-md-9'>\
          <div class='box box-" + color+"'>\
            <div class='box-header with-border'>\
              <h3 class='box-title'>Read Mail</h3>\
              <div class='box-tools pull-right'>\
                <a href='#' class='btn btn-box-tool' data-toggle='tooltip' title='' data-original-title='Previous'><i class='fa fa-chevron-left'></i></a>\
                <a href='#' class='btn btn-box-tool' data-toggle='tooltip' title='' data-original-title='Next'><i class='fa fa-chevron-right'></i></a>\
              </div>\
            </div>\
            <div class='box-body no-padding'>\
              <div class='mailbox-read-info'>\
                <h4>Subj: " +	 subj + "</h4>\
                <h5>From: " + from + "\
                  <span class='mailbox-read-time pull-right'>15 Feb. 2016 11:03 PM</span></h5>\
              </div>\
              </div>\
              <div class='mailbox-read-message'>" + msg + 
            "</div>\
            <div class='box-footer'>\
            </div>\
            <div class='box-footer'>\
              <div class='pull-right'>\
                <button type='button' class='btn btn-default'><i class='fa fa-reply'></i> Reply</button>\
                <button type='button' class='btn btn-default'><i class='fa fa-share'></i> Forward</button>\
              </div>\
              <button type='button' class='btn btn-default'><i class='fa fa-trash-o'></i> Delete</button>\
              <button type='button' class='btn btn-default'><i class='fa fa-print'></i> Print</button>\
            </div>\
          </div>\
        </div>"

}