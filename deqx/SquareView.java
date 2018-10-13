package deqx;

/*
  By Roman Andronov
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

import java.util.EventObject;

class SquareView
	extends JLabel
{
	SquareView( DeqXPanel deqxpnl )
	{
		super();

		pnlDeqx = deqxpnl;

		addMouseListener( pnlDeqx.SV_MH );

		setMaximumSize( SQUARE_VIEW_DIM );
		setMinimumSize( SQUARE_VIEW_DIM );
		setPreferredSize( SQUARE_VIEW_DIM );

		setBorder( RAISED_BRDR );
		setOpaque( true );
		setBackground( SQUARE_CLR );
		setHorizontalAlignment( SwingConstants.CENTER );
		setVerticalAlignment( SwingConstants.CENTER );

		txtClr = getForeground();
	}

	void
	setSquare( Square sq )
	{
		mySquare = sq;
	}

	Square
	getSquare()
	{
		return mySquare;
	}

	void
	press( EventObject eo )
	{
		if ( pnlDeqx.gameOver )
		{
			return;
		}

		if ( pnlDeqx.nextNumber > 1 &&
			mySquare != pnlDeqx.lastSelectedSquare &&
			!mySquare.isVacant() )
		{
			return;
		}

		pnlDeqx.pressedSquare = mySquare;
		setBorder( LOWERED_BRDR );
	}

	void
	release( EventObject eo )
	{
		Square		prsdSqr = pnlDeqx.pressedSquare;
	
		pnlDeqx.pressedSquare = null; // Release the press event

		setBorder( RAISED_BRDR );

		if ( eo instanceof MouseEvent )
		{
			/*
			  Make sure that the mouse event occurred
			  over the originating square

			  Point in the originating square's coordinates, the
			  one that originated the mouse press event
			 */
			MouseEvent	me = ( MouseEvent )eo;

			/*
			  Point in the originating cell's coordinates, the
			  one that originated the mouse press event
			 */
			Point	    ocvp = me.getPoint();

			/*
			  Point in the Board's coordinates
			 */
			Point	    brdp = SwingUtilities.convertPoint( this, ocvp, pnlDeqx.pnlBoard );

			/*
			  Over what component was the mouse released?
			 */
			Component   comp = SwingUtilities.getDeepestComponentAt( pnlDeqx.pnlBoard, brdp.x, brdp.y );
			if ( comp == null )
			{
				return;
			}

			if ( !( comp instanceof SquareView ) )
			{
				return;
			}

			// Destination square
			SquareView	destsv = ( SquareView )comp;

			/*
			  No op if releasing the mouse over the square view
			  that did not originate the mouse press event
			 */
			Square		destsq = destsv.getSquare();
			if ( destsq != prsdSqr )
			{
				return;
			}
		}

		pnlDeqx.setNumber( mySquare );
	}

	void
	setClue( boolean v )
	{
		String		txt = "";

		if ( v )
		{
			txt = "?";
		}

		setText( txt );
	}

	void
	setValue( int v )
	{
		setText( "" + v );
		setBackground( HI_LITE_CLR );
	}

	void
	clear()
	{
		setText( "" );
		setForeground( txtClr );
		setBackground( SQUARE_CLR );
	}

	void
	setRegularTextColor()
	{
		setForeground( txtClr );
	}

	void
	setLastSelectedTextColor()
	{
		setForeground( TXT_CLR_LAST_SELECTED );
	}

	static final int			SQUARE_VIEW_SIZE = 37;
	static final Dimension			SQUARE_VIEW_DIM =
		new Dimension( SQUARE_VIEW_SIZE, SQUARE_VIEW_SIZE );

	static final BevelBorder		RAISED_BRDR = new BevelBorder( BevelBorder.RAISED );
	static final BevelBorder		LOWERED_BRDR = new BevelBorder( BevelBorder.LOWERED );

	static final Color			HI_LITE_CLR = new Color( 197, 213, 203 );
	static final Color			SQUARE_CLR = new Color( 227, 224, 207 );
	static final Color			X_CLR = new Color( 114, 120, 116 );
	static final Color			O_CLR = new Color( 114, 120, 116 );
	static final Color			TXT_CLR_LAST_SELECTED = new Color( 153, 0, 0 );

	private Color				txtClr;
	
	private final DeqXPanel			pnlDeqx;
	private Square				mySquare;
}
