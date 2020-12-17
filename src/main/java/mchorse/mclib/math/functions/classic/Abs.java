package mchorse.mclib.math.functions.classic;

import mchorse.mclib.math.IValue;
import mchorse.mclib.math.functions.Function;

/**
 * Absolute value function 
 */
public class Abs extends Function
{
    public Abs(IValue[] values, String name) throws Exception
    {
        super(values, name);
    }

    @Override
    public int getRequiredArguments()
    {
        return 1;
    }

    @Override
    public double get()
    {
        return Math.abs(this.getArg(0));
    }
}