package deqx;

/*
  By Roman Andronov
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.RootPaneContainer;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;

class DeqXPanel
	extends JPanel
	implements ActionListener
{
	DeqXPanel( RootPaneContainer rpc )
	{
		super();
		
		SV_MH = new SquareViewMouseHandler();

		init( rpc );
	}

	public void
	actionPerformed( ActionEvent ae )
	{
		Object		o = ae.getSource();

		if ( o instanceof JButton )
		{
			JButton		jb = ( JButton )o;

			if ( jb == jbNewGame )
			{
				newGame();
			}
		}
		else if ( o instanceof JCheckBox )
		{
			JCheckBox	chkb = ( JCheckBox )o;

			if ( chkb.isSelected() )
			{
				handleClues( CMD_SHOW_CLUES );
			}
			else
			{
				handleClues( CMD_HIDE_CLUES );
			}
		}
	}

	private void
	init( RootPaneContainer rpc )
	{
		gui = new DeqXGui( this );

		gui.init( rpc );

		newGame();
	}

	private void
	newGame()
	{
		gameOver = false;
		nextNumber = 1;
		lastSelectedSquare = null;
		lblGameState.setText( "Game: on" );

		for ( int r = 0; r < BOARD_SIZE; r++ )
		{
			for ( int c = 0; c < BOARD_SIZE; c++ )
			{
				board[ r ][ c ].clear();
			}
		}
	}

	void
	setNumber( Square srcSqr )
	{
		int		r = -1;
		int		c = -1;
		int		lsR = -1;
		int		lsC = -1;
		int		nclues = 0;

		if ( srcSqr == lastSelectedSquare )
		{
			/*
			  Undo
			 */

			Square		lss = null;
			int		sval = -1;
			int		srcVal = srcSqr.getValue();

			if ( srcVal > 1 )
			{
				lsR = lastSelectedSquare.getRow();
				lsC = lastSelectedSquare.getColumn();
				for ( int i = 0; i < MOVES.length; i++ )
				{
					r = lsR + MOVES[ i ].getRow();
					c = lsC + MOVES[ i ].getColumn();
					if ( r < 0 || c < 0 ||
						r >= BOARD_SIZE ||
						c >= BOARD_SIZE )
					{
						continue;
					}
					sval = board[ r ][ c ].getValue();
					if ( ( srcVal - 1 ) == sval )
					{
						lss = board[ r ][ c ];
						break;
					}
				}
			}

			handleClues( CMD_HIDE_CLUES );
			srcSqr.clear();
			nextNumber--;
			lastSelectedSquare = lss;
			if ( lastSelectedSquare != null )
			{
				lastSelectedSquare.setLastSelectedTextColor();
			}
			if ( chkbShowClues.isSelected() )
			{
				handleClues( CMD_SHOW_CLUES );
			}

			return;
		}

		if ( !srcSqr.isVacant() )
		{
			return;
		}

		if ( nextNumber > 1 )
		{
			int		srcR = srcSqr.getRow();
			int		srcC = srcSqr.getColumn();
			boolean		legal = false;

			lsR = lastSelectedSquare.getRow();
			lsC = lastSelectedSquare.getColumn();
			for ( int i = 0; i < MOVES.length; i++ )
			{
				r = lsR + MOVES[ i ].getRow();
				c = lsC + MOVES[ i ].getColumn();
				if ( r == srcR && c == srcC )
				{
					legal = true;
					break;
				}
			}

			if ( !legal )
			{
				return;
			}

			if ( chkbShowClues.isSelected() )
			{
				handleClues( CMD_HIDE_CLUES );
			}
		}

		if ( lastSelectedSquare != null )
		{
			lastSelectedSquare.setRegularTextColor();
		}
		srcSqr.setLastSelectedTextColor();
		srcSqr.setValue( nextNumber );
		lastSelectedSquare = srcSqr;
		nextNumber++;

		handleClues( CMD_TEST_EOG );
	}

	void
	handleClues( int cmd )
	{
		int		r = -1;
		int		c = -1;
		int		lsR = -1;
		int		lsC = -1;
		int		nclues = 0;

		if ( lastSelectedSquare == null || gameOver == true )
		{
			return;
		}

		lsR = lastSelectedSquare.getRow();
		lsC = lastSelectedSquare.getColumn();

		for ( int i = 0; i < MOVES.length; i++ )
		{
			r = lsR + MOVES[ i ].getRow();
			c = lsC + MOVES[ i ].getColumn();
			if ( r < 0 || c < 0 || r >= BOARD_SIZE || c >= BOARD_SIZE )
			{
				continue;
			}
			if ( !board[ r ][ c ].isVacant() )
			{
				continue;
			}
			nclues++;
			if ( cmd == CMD_SHOW_CLUES )
			{
				board[ r ][ c ].setClue( true );
			}
			else if ( cmd == CMD_HIDE_CLUES )
			{
				board[ r ][ c ].setClue( false );
			}
			else if ( chkbShowClues.isSelected() )
			{
				board[ r ][ c ].setClue( true );
			}
		}

		if ( cmd == CMD_TEST_EOG && nclues == 0 )
		{
			gameOver = true;
			lblGameState.setText( "Game: over" );
		}
	}

	static final int		BOARD_SIZE = 10;

	static final Move[]		MOVES = new Move[]
	{
		new Move( -3, 0 ),
		new Move( 3, 0 ),
		new Move( 0, -3 ),
		new Move( 0, 3 ),
		new Move( -2, 2 ),
		new Move( -2, -2 ),
		new Move( 2, 2 ),
		new Move( 2, -2 )
	};

	static final int		CMD_SHOW_CLUES = 0;
	static final int		CMD_HIDE_CLUES = 1;
	static final int		CMD_TEST_EOG = 2;

	final SquareViewMouseHandler	SV_MH;

	DeqXGui				gui = null;

	JPanel				pnlMain = null;
	JPanel				pnlBoard = null;

	JPanel				pnlCtrls = null;
	JButton				jbNewGame = null;

	JCheckBox			chkbShowClues = null;
	JLabel				lblGameState = null;

	Square[][]			board;
	boolean				gameOver = true;
	int				nextNumber = 1;
	Square				pressedSquare = null;
	Square				lastSelectedSquare = null;
}
