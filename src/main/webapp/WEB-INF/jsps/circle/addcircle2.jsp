<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
<title>添加朋友圈</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="./css/circle.css" type="text/css">
<link rel="stylesheet" href="./css/bootstrap.min.css" type="text/css">
<script src="./js/jquery.min.js"></script>
<script src="./js/upload.js"></script>
<script src="./js/mobileBUGFix.min.js"></script>

</head>
<s:fielderror />
<body>

	<form id="sform" namespace="/circle" action="circle/addCircle.action"
		method="post" enctype="multipart/form-data"  >
		<input type="file" id="fileid1"/>
		<!-- 发送按钮 -->
		<div class="btnsend">
			<input type="button" value="发送" class="btn btn-success sendbtn"
				onClick="check()"/>
		</div>
		<input type="hidden" name="circle.sender.id"
			value="${sessionScope.myId }" />
		<!-- 文字区域 -->
		<textarea class="form-control" rows="5" id="inputtext"
			name="circle.content" placeholder="这一刻的想法……"></textarea>
		<!-- <!-- 隐藏的内容 -->
		<div class="hiddendiv">
			<!-- 	<input type="file" id="fileid" style="display:none"  onchange="previewImage(this)" class="files" 
			name="img" />  -->
		</div>
		<!-- 	添加图片 -->

		<div class="addimgborder" onclick="clickfile()">+</div>

	</form>
<img id="img" src="img/support.png"/>
<textarea rows="1" cols="1" id="imgurl"></textarea>
</body>
<script>
	var num = 1;
	/* 每次添加图片，就创建一个input标签 */
	function clickfile() {
		$(document)
				.ready(
						function() {
							if (num <= 9) {
								$(".btnsend")
										.after(
												"<input type='file' id='fileid"
														+ num
														+ "' style='display:none'  onchange='previewImage(this)' class='files' name='img' /> ");
								$("#fileid" + num).click();
								/* $(".addimgborder")
										.after(
												"<div id='preview"+num+"' class='previmg'></div>"); */
							} else {
								alert("很抱歉，最多支持9张图片！！！")
							}
						});

	}

	
	/* 如果表单全为空，则提交失败 */
	/* function check() {
		$(function(){
			var text=$(".form-control").val();
			if((text==""||text==null)&&num<2){
			alert("请输入内容！！！");
			}else{
			document.getElementById('sform').submit();
			}
		});
		
	} */
	
 	 
	 $(document).ready(function(){
	 $('#fileid1').UploadImg({
        url : 'circle/addCircle.action',
        width : '320',
        //height : '200',
        quality : '0.4', //压缩率，默认值为0.8
        // 如果quality是1 宽和高都未设定 则上传原图
        mixsize : '10000000',
        //type : 'image/png,image/jpg,image/jpeg,image/pjpeg,image/gif,image/bmp,image/x-png',
        before : function(blob){
        alert(blob);
            $('#img').attr('src',blob);
        },
        error : function(res){
         alert("失败了");
            $('#img').attr('src','');
            $('#error').html(res);
        },
        success : function(res){
        alert("成功了");
            $('#imgurl').val(res);
        }
    });
	 });
	
 </script>
</html>
