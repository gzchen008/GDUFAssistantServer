$(document)
		.ready(
				function() {
					$("#admin_name")
							.blur(
									function() {
										var admin_name = $("#admin_name").val();

										$
												.get(
														"/${pageContext.request.contextPath}/club/admin_checkAdmin.action?admin_name="
																+ admin_name,
														function(data, status) {

															var length = data.length;
															if (length == 0) {
																$("p").empty();
																var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
																if (admin_name
																		.match(reg)) {
																	$(
																			"#admin_name")
																			.after(
																					"<p style='color:green'>恭喜你,用户"
																							+ admin_name
																							+ "可以注册</p>");
																} else {
																	$(
																			"#admin_name")
																			.after(
																					"<p style='color:red'>邮箱格式不正确,请重新输入</p>");
																}

															} else {
																$("p").empty();
																$("#admin_name")
																		.after(
																				"<p style='color:red'>"
																						+ admin_name
																						+ "已经存在</p>");
															}

														});
									});
					$("#register_submit").on("click", function() {
						var $admin_name = $("#admin_name").val();
						var $admin_password = $("#admin_password").val();
						var $rePassword = $("#rePassword").val();
						var $club_name = $("#club_name").val();
						var $club_summary = $("#club_summary").val();
						var $club_setup = $("#club_setup").val();
						var form = $("#regiester_form");
						if ($admin_name.length == 0) {
							alert("请正确输入邮箱");
							form.submit(false);
							// location.reload();
							return;
						}

						if ($admin_password.length == 0) {
							alert("密码不能为空");
							form.submit(false);
							// location.reload();
							return;
						} else {
							if ($rePassword.length == 0) {
								alert("请重复输入密码");
								form.submit(false);
								// location.reload();
								return;
							}
							if ($admin_password != $rePassword) {
								alert("输入两次密码不一致");
								form.submit(false);
								// location.reload();
								return;
							}
						}
						if ($club_name == 0) {
							alert("请输入社团名称");
							form.submit(false);
							// location.reload();
							return;
						}
						if ($club_summary.length == 0) {
							alert("请输入社团简介信息")
							form.submit(false);
							// location.reload();
							return;
						} else {
							if ($club_summary.length <= 60) {
								alert("简介信息内容不少于60个字");
								form.submit(false);
								// location.reload();
								return;
							}
						}
					});
					$("#validateImg").bind("click", function() {
						var dateTime = new Date().getTime();
						var src = $(this).attr("src");
						$(this).attr("src", src + "?" + dateTime);
					});
				});
