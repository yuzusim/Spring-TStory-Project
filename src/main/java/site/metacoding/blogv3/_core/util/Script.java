package site.metacoding.blogv3._core.util;

public class Script {

    public static String back(String msg) {
        StringBuffer sb = new StringBuffer(); // StringBuffer 객체 생성
        sb.append("<script>"); // HTML 스크립트 태그 추가
        sb.append("alert('"+msg+"');");  // 경고창 메시지 추가
        sb.append("history.back();"); // 이전 페이지로 돌아가는 JavaScript 코드 추가
        sb.append("</script>"); // 종료 스크립트 태그 추가
        return sb.toString(); // StringBuffer의 내용을 문자열로 반환
    }

}
