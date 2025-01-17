package jobkorea.controller;

import java.util.ArrayList;

import jobkorea.model.dao.PostDao;
import jobkorea.model.dto.EnterpriseDto;
import jobkorea.model.dto.PostDto;

public class PostController {
	
	// + 싱글톤
	private static PostController instance = new PostController();
	private PostController() {}
	public static PostController getInstance() { return instance; }
	// - 싱글톤
	
	// 공고등록 컨트롤러 메소드
	public boolean pRegister( PostDto postDto , int loginEno ) {
		boolean result = PostDao.getInstance().pRegister( postDto , loginEno );
		return result;
	} // f end
	
	// 공고수정 컨트롤러 메소드
	public boolean pUpdate( PostDto postDto , ArrayList<Integer> array ) {
		boolean state = false;
		for( int i = 0 ; i <= array.size() - 1 ; i++ ) {
			int arrays = array.get(i);
			
			if( arrays == postDto.getPno() ) {
				state = true;
				break;
			}// if end
		} // for end
		if( state == false ) {
			return false;
		} // if end
		
		boolean result = PostDao.getInstance().pUpdate( postDto );
		return result;
	} // f end
	
	// 공고삭제 컨트롤러 메소드
	public boolean pDelete( ArrayList<Integer> array , int pno ) {
		boolean state = false; // boolean 타입 state 기존상태 false
		for( int i = 0 ; i <= array.size() -1; i++ ) { // arraylist 배열순회
			int arrays = array.get(i); // array순회하면서 arrays에 array[i]를 담음
			if( arrays == pno ) { // arrays안에 담긴 번호랑 pno가 같으면 state true로 변경
				state = true;
				break;
			} // if end
		} // for end
		if( state == false ) {
			return false;
		} // if end
		
		boolean result = PostDao.getInstance().pDelete( pno );
		return result;
	} // f end
	
	// 지원관리 컨트롤러 메소드
	public boolean pCheck( ArrayList<Integer> array , int ano ) {
		boolean state = false; // boolean 타입 state 기존상태 false
		for( int i = 0 ; i <= array.size() -1; i++ ) {
			int arrays = array.get(i); // array순회하면서 arrays에 array[i]를 담음
			if( arrays == ano ) { // arrays안에 담긴 번호랑 ano가 같으면 state true로 변경
				state = true;
				break;
			} // if end
		} // for end
		if( state == false ) {
			return false;
		} // if end
		
		boolean result = PostDao.getInstance().pCheck( ano );
		return result;
	}
	
	// 내정보보기 컨트롤러 메소드
	public EnterpriseDto pMyinfo( int loginEno ) {
		EnterpriseDto pMyinfo = PostDao.getInstance().pMyinfo( loginEno );
		return pMyinfo;
	}
	
} // c end
