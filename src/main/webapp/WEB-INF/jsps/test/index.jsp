<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统api测试</title>
<link href="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6">
			  <div class="panel panel-info">
    			<div class="panel-heading">Api测试</div>
				<div class="panel-body">
					<form id="paramsForm" class="form form-horizontal">
						<div class="form-group">
							<label class="control-label">方法</label>
							<select  id="selectMethod" class="form-control">
								<option value="post" selected="selected">post</option>
								<option value="get">get</option>
							</select>
						</div>
						<div class="form-group">
							<label class="control-label">URL</label>
							<input type="text" name="url" class="form-control">
						</div>
						<div class="form-group">
							<label class="control-label">参数名</label>
							<input type="text" name="" class="form-control form-keys">
							<label class="control-label">参数值</label>
							<input type="text" name="" class="form-control form-values">
						</div>
						<div class="form-group">
							<label class="control-label">参数名</label>
							<input type="text" name="" class="form-control form-keys">
							<label class="control-label">参数值</label>
							<input type="text" name="" class="form-control form-values">
						</div>
						<div class="form-group">
							<label class="control-label">参数名</label>
							<input type="text" name="" class="form-control form-keys">
							<label class="control-label">参数值</label>
							<input type="text" name="" class="form-control form-values">
						</div>
					</form>
					</div>
				</div>
				<button id="addOneBtn" type="button" class="btn btn-info">添加一条</button>
				<button id="submitBtn" type="button" class="btn btn-primary">提交</button>
			</div>
			<div class="col-md-6">
				<form class="form form-horizontal">
					<div class="form-group">
						<label class="control-label">结果</label>
						<textarea rows="20" id="textBox" class="form-control">
						</textarea>
					</div>
				</form>
			</div>
		</div>
	</div>

<script src="http://cdn.bootcss.com/jquery/3.0.0-beta1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var base = "${baseUrl}";
		$("#addOneBtn").on("click", function(){
				var inputStr = '<div class="form-group"><label class="control-label">参数名</label><input type="text" name="" class="form-control form-keys">';
				inputStr += '<label class="control-label">参数值</label><input type="text" name="" class="form-control form-values"></div>';
				$("#paramsForm").append(inputStr);
		});
		
		$("#submitBtn").on("click", function(){
			var method = $("#selectMethod").val();
			var url = $("input[name=url]").val();
			var data = {};
			$.each($(".form-keys"), function(index, value){
				var key = $(this).val();	
				if(key !=null && key != ""){
					var value = $(this).parent().children(".form-values").val();
					console.log("key:"+key+",value:"+value);
					data[key] = value;
				}
			});
			
			$.ajax({
				url : base + "/" + url,
				type : method,
				data : data,
				success : function(res){
					$("#textBox").val(JSON.stringify(res));
				}
			});
		});
		
	});
</script>
</body>
</html>