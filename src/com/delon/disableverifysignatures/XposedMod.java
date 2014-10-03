package com.delon.disableverifysignatures;

import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class XposedMod implements IXposedHookZygoteInit {

	@Override
	public void initZygote(StartupParam startupParam) throws Throwable {
	    hookVerify1();
	    hookVerify2();
	    hookMessageDigest();
	}
	
	private void hookVerify1(){
		Object[] typeAndCallback = new Object[2];
	    typeAndCallback[0] = byte[].class;
	    typeAndCallback[1] = new XC_MethodHook()
	    {
	      protected void afterHookedMethod(XC_MethodHook.MethodHookParam paramAnonymousMethodHookParam)
	        throws Throwable
	      {
	        XposedBridge.log("DVS_LOG: hooked java.security.Signature_verify(byte[])......");
	        paramAnonymousMethodHookParam.setResult(Boolean.TRUE);
	      }
	    };
	    XposedHelpers.findAndHookMethod("java.security.Signature", null, "verify", typeAndCallback);
	}
	
	private void hookVerify2(){
		Object[] typeAndCallback = new Object[4];
	    typeAndCallback[0] = byte[].class;
	    typeAndCallback[1] = int.class;
	    typeAndCallback[2] = int.class;
	    typeAndCallback[3] = new XC_MethodHook()
	    {
	      protected void afterHookedMethod(XC_MethodHook.MethodHookParam paramAnonymousMethodHookParam)
	        throws Throwable
	      {
	        XposedBridge.log("DVS_LOG: hooked java.security.Signature_verify(byte[],int,int)......");
	        paramAnonymousMethodHookParam.setResult(Boolean.TRUE);
	      }
	    };
	    XposedHelpers.findAndHookMethod("java.security.Signature", null, "verify", typeAndCallback);
	}
	
	private void hookMessageDigest(){
		Object[] typeAndCallback = new Object[3];
	    typeAndCallback[0] = byte[].class;
	    typeAndCallback[1] = byte[].class;
	    typeAndCallback[2] = new XC_MethodHook()
	    {
	      protected void afterHookedMethod(XC_MethodHook.MethodHookParam paramAnonymousMethodHookParam)
	        throws Throwable
	      {
	        XposedBridge.log("DVS_LOG: hooked java.security.MessageDigest_isEqual(byte[],byte[])......");
	        paramAnonymousMethodHookParam.setResult(Boolean.TRUE);
	      }
	    };
	    XposedHelpers.findAndHookMethod("java.security.MessageDigest", null, "isEqual", typeAndCallback);
	}
	
	private void hookCompareSignatures(){
		Object[] typeAndCallback = new Object[3];
	    typeAndCallback[0] = String.class;
	    typeAndCallback[1] = String.class;
	    typeAndCallback[2] = new XC_MethodHook()
	    {
	      protected void afterHookedMethod(XC_MethodHook.MethodHookParam paramAnonymousMethodHookParam)
	        throws Throwable
	      {
	        XposedBridge.log("DVS_LOG: hooked android.content.pm.PackageManager_checkSignatures(String,String)......");
	        paramAnonymousMethodHookParam.setResult(0);
	      }
	    };
	    XposedHelpers.findAndHookMethod("android.content.pm.PackageManager", null, "checkSignatures", typeAndCallback);
	}

	
}
