package deqx;

/*
  By Roman Andronov
 */

class Square
{
	Square( int row, int col, SquareView sv )
	{
		myRow = row;
		myColumn = col;
		myValue = -1;
		mySquareView = sv;
		isClue = false;
	}

	int
	getRow()
	{
		return myRow;
	}

	int
	getColumn()
	{
		return myColumn;
	}

	SquareView
	getView()
	{
		return mySquareView;
	}

	boolean
	isVacant()
	{
		boolean		iv = myValue > 0 ? false : true;

		return iv;
	}

	boolean
	isClue()
	{
		return isClue;
	}

	void
	setClue( boolean v )
	{
		if ( v && myValue > 0 )
		{
			return;
		}

		isClue = v;
		mySquareView.setClue( v );
	}

	int
	getValue()
	{
		return myValue;
	}

	void
	setValue( int v )
	{
		myValue = v;
		mySquareView.setValue( myValue );
	}

	void
	clear()
	{
		myValue = -1;
		isClue = false;
		mySquareView.clear();
	}

	void
	setRegularTextColor()
	{
		mySquareView.setRegularTextColor();
	}

	void
	setLastSelectedTextColor()
	{
		mySquareView.setLastSelectedTextColor();
	}

	private final int		myRow;
	private final int		myColumn;
	private final SquareView	mySquareView;
	private int			myValue; // Varies
	private boolean			isClue; // ditto
}
