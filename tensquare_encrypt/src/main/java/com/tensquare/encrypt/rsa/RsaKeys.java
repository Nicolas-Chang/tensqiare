package com.tensquare.encrypt.rsa;

/**
 * rsa加解密用的公钥和私钥
 * @author Administrator
 *
 */
public class RsaKeys {

	//生成秘钥对的方法可以参考这篇帖子
	//https://www.cnblogs.com/yucy/p/8962823.html

//	//服务器公钥
//	private static final String serverPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6Dw9nwjBmDD/Ca1QnRGy"
//											 + "GjtLbF4CX2EGGS7iqwPToV2UUtTDDemq69P8E+WJ4n5W7Iln3pgK+32y19B4oT5q"
//											 + "iUwXbbEaAXPPZFmT5svPH6XxiQgsiaeZtwQjY61qDga6UH2mYGp0GbrP3i9TjPNt"
//											 + "IeSwUSaH2YZfwNgFWqj+y/0jjl8DUsN2tIFVSNpNTZNQ/VX4Dln0Z5DBPK1mSskd"
//											 + "N6uPUj9Ga/IKnwUIv+wL1VWjLNlUjcEHsUE+YE2FN03VnWDJ/VHs7UdHha4d/nUH"
//											 + "rZrJsKkauqnwJsYbijQU+a0HubwXB7BYMlKovikwNpdMS3+lBzjS5KIu6mRv1GoE"
//											 + "vQIDAQAB";
//
//	//服务器私钥(经过pkcs8格式处理)
//	private static final String serverPrvKeyPkcs8 = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDoPD2fCMGYMP8J"
//				 								 + "rVCdEbIaO0tsXgJfYQYZLuKrA9OhXZRS1MMN6arr0/wT5YniflbsiWfemAr7fbLX"
//				 								 + "0HihPmqJTBdtsRoBc89kWZPmy88fpfGJCCyJp5m3BCNjrWoOBrpQfaZganQZus/e"
//				 								 + "L1OM820h5LBRJofZhl/A2AVaqP7L/SOOXwNSw3a0gVVI2k1Nk1D9VfgOWfRnkME8"
//				 								 + "rWZKyR03q49SP0Zr8gqfBQi/7AvVVaMs2VSNwQexQT5gTYU3TdWdYMn9UeztR0eF"
//				 								 + "rh3+dQetmsmwqRq6qfAmxhuKNBT5rQe5vBcHsFgyUqi+KTA2l0xLf6UHONLkoi7q"
//				 								 + "ZG/UagS9AgMBAAECggEBANP72QvIBF8Vqld8+q7FLlu/cDN1BJlniReHsqQEFDOh"
//				 								 + "pfiN+ZZDix9FGz5WMiyqwlGbg1KuWqgBrzRMOTCGNt0oteIM3P4iZlblZZoww9nR"
//				 								 + "sc4xxeXJNQjYIC2mZ75x6bP7Xdl4ko3B9miLrqpksWNUypTopOysOc9f4FNHG326"
//				 								 + "0EMazVaXRCAIapTlcUpcwuRB1HT4N6iKL5Mzk3bzafLxfxbGCgTYiRQNeRyhXOnD"
//				 								 + "eJox64b5QkFjKn2G66B5RFZIQ+V+rOGsQElAMbW95jl0VoxUs6p5aNEe6jTgRzAT"
//				 								 + "kqM2v8As0GWi6yogQlsnR0WBn1ztggXTghQs2iDZ0YkCgYEA/LzC5Q8T15K2bM/N"
//				 								 + "K3ghIDBclB++Lw/xK1eONTXN+pBBqVQETtF3wxy6PiLV6PpJT/JIP27Q9VbtM9UF"
//				 								 + "3lepW6Z03VLqEVZo0fdVVyp8oHqv3I8Vo4JFPBDVxFiezygca/drtGMoce0wLWqu"
//				 								 + "bXlUmQlj+PTbXJMz4VTXuPl1cesCgYEA6zu5k1DsfPolcr3y7K9XpzkwBrT/L7LE"
//				 								 + "EiUGYIvgAkiIta2NDO/BIPdsq6OfkMdycAwkWFiGrJ7/VgU+hffIZwjZesr4HQuC"
//				 								 + "0APsqtUrk2yx+f33ZbrS39gcm/STDkVepeo1dsk2DMp7iCaxttYtMuqz3BNEwfRS"
//				 								 + "kIyKujP5kfcCgYEA1N2vUPm3/pNFLrR+26PcUp4o+2EY785/k7+0uMBOckFZ7GIl"
//				 								 + "FrV6J01k17zDaeyUHs+zZinRuTGzqzo6LSCsNdMnDtos5tleg6nLqRTRzuBGin/A"
//				 								 + "++xWn9aWFT+G0ne4KH9FqbLyd7IMJ9R4gR/1zseH+kFRGNGqmpi48MS61G0CgYBc"
//				 								 + "PBniwotH4cmHOSWkWohTAGBtcNDSghTRTIU4m//kxU4ddoRk+ylN5NZOYqTxXtLn"
//				 								 + "Tkt9/JAp5VoW/41peCOzCsxDkoxAzz+mkrNctKMWdjs+268Cy4Nd09475GU45khb"
//				 								 + "Y/88qV6xGz/evdVW7JniahbGByQhrMwm84R9yF1mNwKBgCIJZOFp9xV2997IY83S"
//				 								 + "habB/YSFbfZyojV+VFBRl4uc6OCpXdtSYzmsaRcMjN6Ikn7Mb9zgRHR8mPmtbVfj"
//				 								 + "B8W6V1H2KOPfn/LAM7Z0qw0MW4jimBhfhn4HY30AQ6GeImb2OqOuh3RBbeuuD+7m"
//				 								 + "LpFZC9zGggf9RK3PfqKeq30q";

	//服务器公钥
	private static final String serverPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo3asDpM0H+p254yDmtH6\n" +
			"BEhFmxIYxUzHjucR9SiOPb7uokNxrUekmd8CxaedGh8ne47yuroVOo/sbhcqyQ6M\n" +
			"cKeK15bMCSoRc8BKfYEpU+RGexsGsb4He9vWUdxEfP3VPpvyJT/iavjxzAZrz+Yw\n" +
			"G8deS7FVgDjxAbkfoXmDWDHOtvlxDVGrGMmZgFYoNKnYSAd4WvdaoOIn2OvQnfEi\n" +
			"xXvNj9JQfNzmEDqzOu/CtOmu27pY1xLGYCF54MYfFw7IzxxWIERL6IIjihn2E0xI\n" +
			"aOZfik0c7sJrjIY5UDZaXaxk+Pvbf8cQ5huQ0z/34pWxuuVESFt+H3BWW1zUAO0b\n" +
			"RQIDAQAB";

	//服务器私钥(经过pkcs8格式处理)
	private static final String serverPrvKeyPkcs8 = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCjdqwOkzQf6nbn\n" +
			"jIOa0foESEWbEhjFTMeO5xH1KI49vu6iQ3GtR6SZ3wLFp50aHyd7jvK6uhU6j+xu\n" +
			"FyrJDoxwp4rXlswJKhFzwEp9gSlT5EZ7Gwaxvgd729ZR3ER8/dU+m/IlP+Jq+PHM\n" +
			"BmvP5jAbx15LsVWAOPEBuR+heYNYMc62+XENUasYyZmAVig0qdhIB3ha91qg4ifY\n" +
			"69Cd8SLFe82P0lB83OYQOrM678K06a7buljXEsZgIXngxh8XDsjPHFYgREvogiOK\n" +
			"GfYTTEho5l+KTRzuwmuMhjlQNlpdrGT4+9t/xxDmG5DTP/filbG65URIW34fcFZb\n" +
			"XNQA7RtFAgMBAAECggEAbB3q8Xz1pgJ7gO1UgotNlZWDzGxsy9rbxy4Yl5GqezaV\n" +
			"OefoAVd4jHmPqNITPBrND5FeBJldvy1AkRRrrS4dP8nhMHVCIPGdN3VBD7/laOJ8\n" +
			"p6IZjGiHJKokGGLbPdzaNZ4JzA90H5Lmwy6gEpNK0KTOPrxdNAD1j1E1laT8ddD1\n" +
			"Js034vBohy0Bfp/2oNn41eUAdwZ1p5Ox4lEgiHWXRfOnHL9ChfMTJKyMm2gyHEan\n" +
			"JgYSvkg5A81HdR356M6XC6Yl6Fmhwbba9DITz8sCLPB9/0VqfoppLcUa+2HQYgSz\n" +
			"zLbc842wqChlUkce8XNgmhvfIRxDj1L/jGGgWyRGYQKBgQDP/nKYdqyV6Fbx4pxN\n" +
			"xIpwHMpIX5ndfWF2FTRqI/2i1AfEIwykm2XjJl1pBz1cCh0bPPvN6StBc9Fqmshy\n" +
			"l0i7QSSaVgGMVcCFwQHG2Gt7VgxbD0QtHbLbipCT4Sufg6nadxkPo+rbZIgQ43z/\n" +
			"T4nGp2qO+U/uLT33/KXzgQ/pTQKBgQDJMRkVBx0dsjOitebZKdV3v9AcTC/Zt99w\n" +
			"W32BnzvjnNdEJOnp9KY+44L1YfbdQOKn8aeqNdx3uTIvusCkkB43Tq+DhzhxKNZp\n" +
			"qQ4e9ExyYk+FMBBZmKGS8pvO5tA3YIPC/P60Dk3S+WSxdvwaSZN9/5p5avi0+N95\n" +
			"pA9A2r092QKBgQC1IPDKtQ42vc55ds6HKtEkMO43eImVyMpG+tBaXl1SnzLkK2Gn\n" +
			"N2zTE2XlQNi+flA6mDDwcE2rAiEspP5KTFSaPXCStrNCcqrlz96sO2veTGEWK4VD\n" +
			"hMltRo4BOeWzVAVmqrC4Xhb4V+PmUTVZYPqqdMDnIVXqJJwCiZI7K/mfQQKBgGwi\n" +
			"T3DV2fhUBxy019ZwfaNXHOuYpz9FbHpfMh2xD+dOLQUbMf3k2FWEwqZQePaCIVBS\n" +
			"ueLqAAz5JNgyb8gDDbp8OClhTBzF7jfr/QL2GwB/hm33X1JIjs8ERmn19INPBIKQ\n" +
			"p/TumzWBhXU8llMqqHNYJhaMQBBEkxg17VzWNFBBAoGAF41nXL23J0we7R95krPO\n" +
			"veG60SsLeWWo1t2kDuGHgPn01QJ3h9HK3T2YQAYcTxY2fXndNwRVhybMzxcy6h9H\n" +
			"qceD528US/hJedmZpOftZ0ouhLwpig8gsZvqz9HQNzkKUlkYLheL95Onsso3uuo/\n" +
			"jLRC2T4z394VeF/xo3vevtA= ";

	public static String getServerPubKey() {
		return serverPubKey;
	}

	public static String getServerPrvKeyPkcs8() {
		return serverPrvKeyPkcs8;
	}
	
}
