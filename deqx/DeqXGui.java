package deqx;

/*
  By Roman Andronov
 */

import java.awt.Insets;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.RootPaneContainer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JCheckBox;

class DeqXGui
{
	DeqXGui( DeqXPanel pnldeqx )
	{
		pnlDeqx = pnldeqx;
	}

	void
	init( RootPaneContainer rpc )
	{
		GridBagConstraints	gbc = new GridBagConstraints();
		Insets			dfltInsts = gbc.insets;

		gbc.gridx = gbc.gridy = 0;
		gbc.weightx = gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = INSETS;

		rpc.getContentPane().setLayout( new GridBagLayout() );

		pnlDeqx.setLayout( new GridBagLayout() );
		pnlDeqx.setBorder( BorderFactory.createLineBorder( CLRGRAY ) );
		pnlDeqx.setBackground( BOARD_CLR );
		rpc.getContentPane().add( pnlDeqx, gbc );

		pnlDeqx.pnlMain = new JPanel();
		pnlDeqx.pnlMain.setBorder( BorderFactory.createLineBorder( CLRGRAY ) );
		pnlDeqx.pnlMain.setLayout( new GridBagLayout() );
		pnlDeqx.pnlMain.setBackground( BOARD_CLR );
		pnlDeqx.add( pnlDeqx.pnlMain, gbc );

		pnlDeqx.pnlBoard = new JPanel();
		pnlDeqx.pnlBoard.setLayout( new GridBagLayout() );
		gbc.insets = dfltInsts;
		pnlDeqx.pnlMain.add( pnlDeqx.pnlBoard, gbc );
		gbc.insets = INSETS;
		mkBoardPnl();

		gbc.gridy = 1;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		pnlDeqx.pnlCtrls = new JPanel();
		pnlDeqx.pnlCtrls.setBorder( BorderFactory.createLineBorder( CLRGRAY ) );
		pnlDeqx.pnlCtrls.setLayout( new GridBagLayout() );
		pnlDeqx.add( pnlDeqx.pnlCtrls, gbc );
		mkCtrlsPnl();
	}

	private void
	mkBoardPnl()
	{
		SquareView		sv = null;
		GridBagConstraints	gbc = new GridBagConstraints();

		pnlDeqx.pnlBoard.setBackground( BOARD_CLR );

		pnlDeqx.board =
			new Square[ DeqXPanel.BOARD_SIZE ][ DeqXPanel.BOARD_SIZE ];

		gbc.weightx = gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;

		for ( int r = 0; r < DeqXPanel.BOARD_SIZE; r++ )
		{
			for ( int c = 0; c < DeqXPanel.BOARD_SIZE; c++ )
			{
				sv = new SquareView( pnlDeqx );
				pnlDeqx.board[ r ][ c ] = new Square( r, c, sv );
				sv.setSquare( pnlDeqx.board[ r ][ c ] );
				gbc.gridx = c;
				gbc.gridy = r;
				pnlDeqx.pnlBoard.add( sv, gbc );
			}
		}
	}

	private void
	mkCtrlsPnl()
	{
		GridBagConstraints	gbc = new GridBagConstraints();

		gbc.gridx = gbc.gridy = 0;
		gbc.insets = INSETS;

		pnlDeqx.pnlCtrls.setBackground( BOARD_CLR );
		
		pnlDeqx.jbNewGame = new JButton( "New Game" );
		pnlDeqx.jbNewGame.setMnemonic( KeyEvent.VK_N );
		pnlDeqx.jbNewGame.addActionListener( pnlDeqx );
		pnlDeqx.pnlCtrls.add( pnlDeqx.jbNewGame, gbc );

		gbc.gridx = 1;
		pnlDeqx.chkbShowClues = new JCheckBox( "Show Clues" );
		pnlDeqx.chkbShowClues.setBackground( BOARD_CLR );
		pnlDeqx.chkbShowClues.setMnemonic( KeyEvent.VK_C );
		pnlDeqx.chkbShowClues.setSelected( false );
		pnlDeqx.chkbShowClues.addActionListener( pnlDeqx );
		pnlDeqx.pnlCtrls.add( pnlDeqx.chkbShowClues, gbc );

		gbc.gridx = 2;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		pnlDeqx.lblGameState = new JLabel( "Game: on" );
		pnlDeqx.pnlCtrls.add( pnlDeqx.lblGameState, gbc );
	}


	DeqXPanel				pnlDeqx;

	static final Insets			INSETS = new Insets( 5, 5, 5, 5 );

	static final Color			CLRGRAY = Color.GRAY;
	static final Color			CLRLGRAY = Color.LIGHT_GRAY;
	private final Color			BOARD_CLR = new Color( 197, 213, 203 );
}
