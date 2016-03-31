package com.vanroid.gduf.controller.circle;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.vanroid.gduf.entity.Circle;
import com.vanroid.gduf.entity.CircleMes;
import com.vanroid.gduf.entity.Comment;
import com.vanroid.gduf.service.circle.CircleService;
@Controller("commentAction")
@Scope("prototype")
public class CommentAction extends ActionSupport  {
	HttpServletResponse response=ServletActionContext.getResponse();
	private Comment comment = new Comment();
	Map<String,String> resultMap=new HashMap<String,String>();
	private CircleService circleService;
	private int cid;
	/**
	 * 增加评论和点赞
	 * 
	 * @retudongrn
	 */
	public String addComment() {
		//判断是点赞后，根据tid和sender防止重复点赞！
		if("".equals(comment.getText())||comment.getText()==null){
			System.out.println("空的");
			if(!circleService.isSupported(comment)){	//查询是否已经点赞过了
				System.out.println("开启点赞");
				circleService.addComment(comment);
				/*//操作后更新session内容
				List<Circle> circleList=circleService.queryCircles(0, 3);
				ServletActionContext.getRequest().getSession().setAttribute("circles", circleList);
*/					
				//添加通知
				circleService.addNotifaction(new CircleMes(comment.getTid(), comment.getSender(), comment.getReceiver(), "❤", "noread"));

					resultMap.put("result", "success");
				
				return Action.SUCCESS;
			}else{
				//已经点赞过
				System.out.println("取消点赞-----");	
				circleService.deleteComment(circleService.getCid(comment));
				System.out.println("已经删除点赞");
				resultMap.put("result", "fail");
				return Action.SUCCESS;
			}
		}else{
			//评论
			System.out.println("评论内容："+comment.getText());
			circleService.addComment(comment);
			circleService.addNotifaction(new CircleMes(comment.getTid(), comment.getSender(), comment.getReceiver(), comment.getText(), "noread"));

			//操作后更新session内容
			//List<Circle> circleList=circleService.queryCircles(0, 3);
			//ServletActionContext.getRequest().getSession().setAttribute("circles", circleList);
			System.out.println("不为空");
			String strcid=circleService.getCid(comment)+"";
			System.out.println("cid:"+strcid);
			resultMap.put("cid",strcid);
			return Action.SUCCESS;
		}
		
	}

	

	/**
	 * 删除评论去取消点赞
	 * 
	 * @return
	 */
	public String deleteComment() {
		circleService.deleteComment(comment.getCid());
		return Action.SUCCESS;
	}
	/**
	 * 异步请求删除评论
	 * @param cid ajax传递过来要删除的comment的id
	 * @return
	 */
	public String deleteCommentJson() {
		try{
		circleService.deleteComment(cid);
		System.out.println(cid+"删除成功！");
		resultMap.put("result", "success");
		
		}catch(Exception e){
			resultMap.put("result", "fail");
		}
		return Action.SUCCESS;
	}


	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public CircleService getCircleService() {
		return circleService;
	}
	@Resource
	public void setCircleService(CircleService circleService) {
		this.circleService = circleService;
	}



	public Map<String, String> getResultMap() {
		return resultMap;
	}



	public void setResultMap(Map<String, String> resultMap) {
		this.resultMap = resultMap;
	}



	public int getCid() {
		return cid;
	}



	public void setCid(int cid) {
		this.cid = cid;
	}
	
}
