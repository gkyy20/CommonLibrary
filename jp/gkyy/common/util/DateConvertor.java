package jp.gkyy.common.util;

import java.util.Calendar;
import java.util.StringTokenizer;

public class DateConvertor {
	/**
	 * �w�肳�ꂽ���t�E������������A�\�ł���� 
	 * Calendar�N���X�ɕϊ����܂��B
	 * �ȉ��̌`���̓��t�������ϊ��ł��܂��B
	 * 
	 * ���ϊ��\�Ȍ`���͈ȉ��ƂȂ�܂��B
	 *  yyyy/MM/dd
	 *  yy/MM/dd
	 *  yyyy-MM-dd
	 *  yy-MM-dd
	 *  yyyyMMdd
	 * 
	 * ��L�Ɉȉ��̎��ԃt�B�[���h���g�ݍ��킳�ꂽ���
	 * �ł��L���ł��B
	 *  HH:mm 
	 *  HH:mm:ss 
	 *  HH:mm:ss.SSS
	 *  
	 * @param strDate ���t�E����������B
	 * @return �ϊ����Calendar�N���X�B
	 * @throws IllegalArgumentException 
	 *         ���t�����񂪕ϊ��s�\�ȏꍇ
	 *         �܂��́A�������Ă���ꍇ�i��F2000/99/99�j�B
	 */
	public static Calendar toCalendar(String strDate) throws IllegalArgumentException{
	    strDate = format(strDate);
	    Calendar cal = Calendar.getInstance();
	    cal.setLenient(false);

	    int yyyy = Integer.parseInt(strDate.substring(0,4));
	    int MM = Integer.parseInt(strDate.substring(5,7));
	    int dd = Integer.parseInt(strDate.substring(8,10));
	    int HH = cal.get(Calendar.HOUR_OF_DAY);
	    int mm = cal.get(Calendar.MINUTE);
	    int ss = cal.get(Calendar.SECOND);
	    int SSS = cal.get(Calendar.MILLISECOND);
	    cal.clear();
	    cal.set(yyyy,MM-1,dd);
	    int len = strDate.length();
	    switch (len) {
	        case 10:
	            break;
	        case 16: // yyyy/MM/dd HH:mm
	            HH = Integer.parseInt(strDate.substring(11,13));
	            mm = Integer.parseInt(strDate.substring(14,16));
	            cal.set(Calendar.HOUR_OF_DAY,HH);
	            cal.set(Calendar.MINUTE,mm);
	            break;
	        case 19: //yyyy/MM/dd HH:mm:ss
	            HH = Integer.parseInt(strDate.substring(11,13));
	            mm = Integer.parseInt(strDate.substring(14,16));
	            ss = Integer.parseInt(strDate.substring(17,19));
	            cal.set(Calendar.HOUR_OF_DAY,HH);
	            cal.set(Calendar.MINUTE,mm);
	            cal.set(Calendar.SECOND,ss);
	            break;
	        case 23: //yyyy/MM/dd HH:mm:ss.SSS
	            HH = Integer.parseInt(strDate.substring(11,13));
	            mm = Integer.parseInt(strDate.substring(14,16));
	            ss = Integer.parseInt(strDate.substring(17,19));
	            SSS = Integer.parseInt(strDate.substring(20,23));
	            cal.set(Calendar.HOUR_OF_DAY,HH);
	            cal.set(Calendar.MINUTE,mm);
	            cal.set(Calendar.SECOND,ss);
	            cal.set(Calendar.MILLISECOND,SSS);
	            break;
	        default :
	            throw new IllegalArgumentException(
	                    "�����̕�����["+ strDate +
	                    "]�͓��t������ɕϊ��ł��܂���");
	    }
	    return cal;
	}

	/**
	 * �l�X�ȓ��t�A������������f�t�H���g�̓��t�E�����t�H�[�}�b�g
	 * �֕ϊ����܂��B
	 * 
	 * ���f�t�H���g�̓��t�t�H�[�}�b�g�͈ȉ��ɂȂ�܂��B
	 *     ���t�����̏ꍇ�Fyyyy/MM/dd
	 *     ���t+�����̏ꍇ�Fyyyy/MM/dd HH:mm:ss.SSS
	 * 
	 * @param str �ϊ��Ώۂ̕�����
	 * @return �f�t�H���g�̓��t�E�����t�H�[�}�b�g
	 * @throws IllegalArgumentException 
	 *     ���t�����񂪕ϊ��s�\�ȏꍇ 
	 */
	private static String format(String str) throws IllegalArgumentException{
	    if (str == null || str.trim().length() < 8) {
	        throw new IllegalArgumentException(
	                "�����̕�����["+ str +
	                "]�͓��t������ɕϊ��ł��܂���");
	    }
	    str = str.trim();
	    String yyyy = null; String MM = null; String dd = null; 
	    String HH = null; String mm = null; 
	    String ss = null;String SSS = null;  
	    // "-" or "/" �������ꍇ
	    if (str.indexOf("/")==-1 && str.indexOf("-")==-1) {
	        if (str.length() == 8) {
	            yyyy = str.substring(0,4);
	            MM = str.substring(4,6);
	            dd = str.substring(6,8);
	            return yyyy+"/"+MM+"/"+dd;
	        }
	        yyyy = str.substring(0,4);
	        MM = str.substring(4,6);
	        dd = str.substring(6,8);
	        HH = str.substring(9,11);
	        mm = str.substring(12,14);
	        ss = str.substring(15,17);
	        return yyyy+"/"+MM+"/"+dd+" "+HH+":"+mm+":"+ss;
	    }
	    StringTokenizer token = new StringTokenizer(str,"_/-:. ");
	    StringBuffer result = new StringBuffer();
	    for(int i = 0; token.hasMoreTokens(); i++) {
	        String temp = token.nextToken();
	        switch(i){
	            case 0:// �N�̕���
	                yyyy = fillString(str, temp, "L", "20", 4);
	                result.append(yyyy);  
	                break;
	            case 1:// ���̕���
	                MM = fillString(str, temp, "L", "0", 2); 
	                result.append("/"+MM);  
	                break;
	            case 2:// ���̕���
	                dd = fillString(str, temp, "L", "0", 2); 
	                result.append("/"+dd);  
	                break;
	            case 3:// ���Ԃ̕���
	                HH = fillString(str, temp, "L", "0", 2);
	                result.append(" "+HH);  
	                break;
	            case 4:// ���̕���
	                mm = fillString(str, temp, "L", "0", 2); 
	                result.append(":"+mm);  
	                break;
	            case 5:// �b�̕���
	                ss = fillString(str, temp, "L", "0", 2); 
	                result.append(":"+ss);  
	                break;
	            case 6:// �~���b�̕���
	                SSS = fillString(str, temp, "R", "0", 3); 
	                result.append("."+SSS);  
	                break;
	        }
	    }
	    return result.toString();
	}
	private static String fillString(String strDate, String str, 
	                             String position, String addStr, 
	                             int len) throws IllegalArgumentException{
	    if (str.length() > len) {
	        throw new IllegalArgumentException(
	            "�����̕�����["+ strDate +
	            "]�͓��t������ɕϊ��ł��܂���");
	    }
	    return fillString(str, position, len,addStr);
	}

	/**
	 * ������[str]�ɑ΂��āA��[���镶����[addStr]�� 
	 * [position]�̈ʒu��[len]�ɖ������܂ő}�����܂��B
	 * 
	 * ��[str]��null��󃊃e�����̏ꍇ�ł�[addStr]��
	 * [len]�ɖ������܂ő}���������ʂ�Ԃ��܂��B
	 * @param str �Ώە�����
	 * @param position �O�ɑ}�� �� L or l ��ɑ}�� �� R or r
	 * @param len ��[����܂ł̌���
	 * @param addStr �}�����镶����
	 * @return �ϊ���̕�����B
	 */
	private static String fillString(String str, String position, 
	        int len,
	        String addStr) throws IllegalArgumentException{
	    if (addStr == null || addStr.length() == 0) {
	        throw new IllegalArgumentException
	            ("�}�����镶����̒l���s���ł��BaddStr="+addStr);
	    }
	    if (str == null) {
	        str = "";
	    }
	    StringBuffer buffer = new StringBuffer(str);
	    while (len > buffer.length()) {
	        if (position.equalsIgnoreCase("l")) {
	            int sum = buffer.length() + addStr.length();
	            if (sum > len) {
	                addStr = addStr.substring
	                    (0,addStr.length() - (sum - len));
	                buffer.insert(0, addStr);
	            }else{
	                buffer.insert(0, addStr);
	            }
	        } else {
	            buffer.append(addStr);
	        }
	    }
	    if (buffer.length() == len) {
	        return buffer.toString();
	    }
	    return buffer.toString().substring(0, len);
	}
}
