package week567_miniJVM.field;

import structure.week1.ArrayList;
import week567_miniJVM.constant.ConstantPool;
import week567_miniJVM.loader.ByteCodeIterator;
public class Field {
	private int accessFlag,nameIndex,descIndex;
	private ConstantPool pool;
	public Field( int accessFlag, int nameIndex, int descIndex,ConstantPool pool) {
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descIndex = descIndex;
		this.pool = pool;
	}
	public void parse(ConstantPool pool,ByteCodeIterator iter){
        int propNum = iter.nextU2ToInt();
        while(propNum>0){
            propNum -= 1;
            int prop = iter.nextU2ToInt();
           // TODO
        }
		return ;
	}
}