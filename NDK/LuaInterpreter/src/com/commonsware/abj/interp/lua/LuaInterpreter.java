/***
	Copyright (c) 2010 CommonsWare, LLC
	
	Licensed under the Apache License, Version 2.0 (the "License"); you may
	not use this file except in compliance with the License. You may obtain
	a copy of the License at
		http://www.apache.org/licenses/LICENSE-2.0
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/

package com.commonsware.abj.interp.lua;

import android.os.Bundle;
import com.commonsware.abj.interp.I_Interpreter;
import com.commonsware.abj.interp.InterpreterService;

public class LuaInterpreter implements I_Interpreter {
	public static native String executeLua(String script);
	
	public Bundle executeScript(Bundle input) {
		Bundle output=new Bundle(input);
		String script=input.getString(InterpreterService.SCRIPT);
		
		if (script!=null) {
			try {	
				String eval_result=executeLua(script);
				
				output.putString("result", eval_result.toString());
			}
			catch (Throwable t) {
				output.putString("error", t.getMessage());
			}
		}
		
		return(output);
	}
	
	static { System.loadLibrary("lua"); }
}