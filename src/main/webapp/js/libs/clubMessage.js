/** **********************************club_management.jsp***************************** */
/*
 * 删除信息
 */
function deleteItem(id) {
	if (id != -1) {
		var con = confirm("你要删除这条消息吗");
		if (con == true)
			var xmlhttp = createHttpXML();
		xmlhttp.open("GET",
				"${pageContext.request.contextPath}/clubMessage_deleteItem?id="
						+ id + "&state=single", true);
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				location.reload();
			}
		}
		xmlhttp.send(null);
	} else {
		var items = document.getElementsByName("clubMessage_id");
		var count = 0;
		array = new Array();
		for (var i = 0; i < items.length; i++) {
			if (items[i].checked) {
				array[i] = items[i].value;
				count++;
			}
		}
		if (count != 0) {
			var con = confirm("你确定删除" + count + "条消息吗？");
			var myform = document.getElementById("myform");
			myform.action = "${pageContext.request.contextPath}/clubMessage_deleteItem?state=batch";
			myform.submit();
		} else {
			alert("请选择需要删除的信息");
		}
	}

}

/*
 * 创建异步上传对象
 */
function createHttpXML() {
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	return xmlhttp;
}
/*
 * checkbox全选or全不选
 */

function allSelect() {
	var allChose = document.getElementById("allChose");
	var items = document.getElementsByName("clubMessage_id");
	if (allChose.checked) {
		for (var i = 0; i < items.length; i++) {
			items[i].checked = true;
		}
	} else {
		for (var i = 0; i < items.length; i++) {
			items[i].checked = false;
		}
	}
}
/*
 * 根据给定状态更新列表
 */
function updateList() {
	var select = document.getElementById("search-sort");
	var sortValue = select.value;
	if (sortValue != "#") {
		var headform = document.getElementById("headform");
		headform.action = "${pageContext.request.contextPath}/clubMessage_updateState.action?state="
				+ sortValue;
		headform.submit();
	}
}
/** **********************************club_management.jsp***************************** */

/** ******************************club_insert.jsp*********************************** */
function getselectElement() {
	var xmlhttp = createHttpXML();
	xmlhttp.open("GET",
			"${pageContext.request.contextPath}/club_getAllClubs.action", true);
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var returned = xmlhttp.responseText;
			var obj = eval(returned);
			var select = document.getElementById("catid");
			for (var i = 0; i < obj.length; i++) {
				var option = document.createElement("option");
				option.appendChild(document.createTextNode(obj[i].club_name));
				option.setAttribute("value", obj[i].id);
				select.appendChild(option);
			}
			var club_id = '${requestScope.clubMessage.club.id}';
			for (var i = 0; i < select.length; i++) {
				if (select.options[i].value == club_id) {
					select.options[i].selected = true;
					break;
				}
			}
		}
	}

	xmlhttp.send(null);
}
function checkTheForm(myform) {
	var club_id = document.getElementById("catid").value;
	var title = document.getElementById("title").value;
	if (club_id == -1) {
		alert("请选择社团");
		return false;
	}

	if (title == "" || title.length == 0) {
		alert("标题不能为空,请输入标题");
		return false;
	}
	return true;
}
/** ******************************club_message_insert.jsp*********************************** */
/** *******************************club_message_update.jsp******************************** */
function checkTheForm(myform) {
	var club_id = document.getElementById("catid").value;
	var title = document.getElementById("title").value;
	if (club_id == -1) {
		alert("请选择社团");
		return false;
	}

	if (title == "" || title.length == 0) {
		alert("标题不能为空,请输入标题");
		return false;
	}
	return true;
}
function updateMessage(msg_id) {

	var myform = document.getElementById("myform");
	myform.action = "${pageContext.request.contextPath}/clubMessage_updateMessage?msg_id="
			+ msg_id;
}
/** *******************************club_message_update.jsp******************************** */
