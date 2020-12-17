package mchorse.mclib.math.functions.utility;

import mchorse.mclib.math.IValue;
import mchorse.mclib.math.functions.Function;
import mchorse.mclib.utils.Interpolations;

public class LerpRotate extends Function
{
	public LerpRotate(IValue[] values, String name) throws Exception
	{
		super(values, name);
	}

	@Override
	public int getRequiredArguments()
	{
		return 3;
	}

	@Override
	public double get()
	{
		return Interpolations.lerpYaw(this.getArg(0), this.getArg(1), this.getArg(2));
	}
}