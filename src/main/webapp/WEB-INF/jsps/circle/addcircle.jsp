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
<script src=".js/upload.js"></script>
<script src=".js/mobileBUGFix.min.js"></script>

</head>
<s:fielderror />
<body>
	<form id="sform" namespace="/circle" action="circle/addCircle.action"
		method="post" enctype="multipart/form-data"  >
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
								$(".addimgborder")
										.after(
												"<div id='preview"+num+"' class='previmg'></div>");
							} else {
								alert("很抱歉，最多支持9张图片！！！")
							}
						});

	}

	/*   预览图片 */
	function previewImage(file) {

		var MAXWIDTH = 100;
		var MAXHEIGHT = 100;
		var div = document.getElementById('preview' + num);
		if (file.files && file.files[0]) {
			div.innerHTML = '<img id=imghead> ';
			var img = document.getElementById('imghead');
			img.onload = function() {
				var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT,
						img.offsetWidth, img.offsetHeight);
				img.width = rect.width;
				img.height = rect.height;
				img.style.marginTop = rect.top + 'px';
			}
			var reader = new FileReader();
			reader.onload = function(evt) {
				img.src = evt.target.result;
			}
			reader.readAsDataURL(file.files[0]);
		} else //兼容IE
		{
			var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
			file.select();
			var src = document.selection.createRange().text;
			div.innerHTML = '<img id=imghead>';
			var img = document.getElementById('imghead');
			img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
			var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth,
					img.offsetHeight);
			status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width
					+ ',' + rect.height);
			div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
		}
		num++;
	}

	function clacImgZoomParam(maxWidth, maxHeight, width, height) {
		var param = {
			top : 0,
			left : 0,
			width : width,
			height : height
		};
		if (width > maxWidth || height > maxHeight) {
			rateWidth = width / maxWidth;
			rateHeight = height / maxHeight;

			if (rateWidth > rateHeight) {
				param.width = maxWidth;
				param.height = Math.round(height / rateWidth);
			} else {
				param.width = Math.round(width / rateHeight);
				param.height = maxHeight;
			}
		}

		param.left = Math.round((maxWidth - param.width) / 2);
		param.top = Math.round((maxHeight - param.height) / 2);
		return param;
	}
	/* 如果表单全为空，则提交失败 */
	function check() {
		$(function(){
			var text=$(".form-control").val();
			if((text==""||text==null)&&num<2){
			alert("请输入内容！！！");
			}else{
			document.getElementById('sform').submit();
			}
		});
		
	}
</script>
</html>
