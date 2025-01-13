package jobkorea.controller;

import jobkorea.model.dao.PostDao;
import jobkorea.model.dto.PostDto;

public class PostController {
	
	// + 싱글톤
	private static PostController instance = new PostController();
	private PostController() {}
	public static PostController getInstance() { return instance; }
	// - 싱글톤
	
	// 공고등록 컨트롤러 메소드
	public boolean pRegister( PostDto postDto ) {
		boolean result = PostDao.getInstance().pRegister( postDto );
		return result;
	} // f end
	
	// 공고수정 컨트롤러 메소드
	public boolean pUpdate( PostDto postDto) {
		boolean result = PostDao.getInstance().pUpdate( postDto );
		return result;
	} // f end
	
	// 공고삭제 컨트롤러 메소드
	
} // c end
