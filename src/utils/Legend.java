package utils;

import processing.core.PApplet;

public class Legend {
    public static void drawLegend(PApplet app) {
    int legendX = 10; 
    int legendY = 10; 
    int boxSize = 20; 
    int spacing = 5;
    int rowHeight = boxSize + spacing;

    app.fill(255, 255, 0);
    app.textSize(14);
    app.text("Legend", legendX, legendY + boxSize);

    int startY = legendY + boxSize + spacing * 2;

    app.fill(0, 123, 127);
    app.rect(legendX, startY, boxSize, boxSize);
    app.fill(255);
    app.text("Right", legendX + boxSize + spacing, startY + boxSize - 5);

    startY += rowHeight;
    app.fill(255, 166, 0);
    app.rect(legendX, startY, boxSize, boxSize);
    app.fill(255);
    app.text("Left", legendX + boxSize + spacing, startY + boxSize - 5);

    startY += rowHeight;
    app.fill(166, 123, 201);
    app.rect(legendX, startY, boxSize, boxSize);
    app.fill(255);
    app.text("Straight", legendX + boxSize + spacing, startY + boxSize - 5);
}

}
