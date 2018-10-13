package deqx;

/*
  By Roman Andronov
 */

class Move
{
	Move( int r, int c )
	{
		myRow = r;
		myCol = c;
	}

	int
	getRow()
	{
		return myRow;
	}

	int
	getColumn()
	{
		return myCol;
	}

	private final int		myRow;
	private final int		myCol;
}
