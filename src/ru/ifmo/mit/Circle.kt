package ru.ifmo.mit

import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.geom.Ellipse2D
import javax.swing.*
import java.awt.MouseInfo
import java.awt.RenderingHints
import javax.swing.JFrame

class Circle : JPanel(), ActionListener {
    var t: Timer = Timer(5, this)
    var x = 0.0
    var y = 0.0
    var velX = 0.0
    var velY = 0.0

    override fun paintComponent(g : Graphics) {
        super.paintComponent(g)
        val g2 : Graphics2D = g as Graphics2D
        //add antialiasing to have beautuful smooth circle
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        val circle: Ellipse2D = Ellipse2D.Double(x, y, 30.0, 30.0)
        g2.fill(circle)
        t.start()
    }

    override fun actionPerformed(e: ActionEvent?) {
        val a = MouseInfo.getPointerInfo()
        val b = a.location
        var xX = b.getX() - 15
        var yY = b.getY() - 35
        xX -= x
        yY -= y
        velX = xX/30
        velY = yY/30
        x += velX
        y += velY
        repaint()
    }

}

fun main() {
    val s = Circle()
    val f = JFrame()
    f.add(s)
    f.isVisible = true
    f.setSize(600, 400)
    f.setExtendedState(f.getExtendedState() or JFrame.MAXIMIZED_BOTH)
    f.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    f.title = "Minimalistic moving circle"
}