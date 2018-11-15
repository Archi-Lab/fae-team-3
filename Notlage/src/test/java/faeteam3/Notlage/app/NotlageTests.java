package faeteam3.Notlage.app;

import faeteam3.Notlage.Bezugsperson;
import faeteam3.Notlage.DVP;
import faeteam3.Notlage.Nachricht;
import faeteam3.Notlage.notlage.model.BezugspersonsArentEqual;
import faeteam3.Notlage.notlage.model.InterfaceNotlage;
import faeteam3.Notlage.notlage.model.Notlage;
import faeteam3.Notlage.notlage.model.Status;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class NotlageTests {

    @Test
    public void newDummy_new_inBearbeitung()
    {
        final Status expected = Status.IN_BEARBEITUNG;
        Status actual;

        InterfaceNotlage n = createDummyInstance();

        actual = n.getStatus();

        Assert.assertEquals(actual,expected);
    }

    @Test
    public void new_new_inBearbeitung()
    {
        final Status expected = Status.IN_BEARBEITUNG;
        Status actual;

        InterfaceNotlage n = createInstance();

        actual = n.getStatus();

        Assert.assertEquals(actual,expected);
    }

    @Test
    public void markBestaetigt_InBearbeitung_Bestaetigt()
    {
        final Status expected = Status.BESTÄTIGT;
        Status actual;

        InterfaceNotlage n = createInstance();
        n.markBestaetigt(new Bezugsperson());
        actual = n.getStatus();

        Assert.assertEquals(actual,expected);
    }

    @Test(expected = IllegalStateException.class)
    public void markBestaetigt_Bestaetigt_IllegalStateException()
    {
        InterfaceNotlage n = createInstance();
        Bezugsperson bp = new Bezugsperson();
        n.markBestaetigt(bp);
        n.markBestaetigt(bp);
    }

    @Test(expected = IllegalStateException.class)
    public void markBestaetigt_Geloest_IllegalStateException()
    {
        InterfaceNotlage n = createInstance();
        Bezugsperson bp = new Bezugsperson();
        n.markBestaetigt(bp);
        n.markGeloest(bp);
        n.markBestaetigt(bp);
    }

    @Test
    public void markBestaetigt_Bestaetigt_Geloest()
    {
        final Status expected = Status.GELOEST;
        Status actual;

        InterfaceNotlage n = createInstance();
        Bezugsperson bp = new Bezugsperson();
        n.markBestaetigt(bp);
        n.markGeloest(bp);
        actual = n.getStatus();

        Assert.assertEquals(actual,expected);
    }

    @Test(expected = BezugspersonsArentEqual.class)
    public void markGeloest_BestaetigtANDBpBestaetigtNotLikeBpGeloest_Geloest()
    {
        InterfaceNotlage n = createInstance();
        Bezugsperson bp1 = new Bezugsperson(1l);
        Bezugsperson bp2 = new Bezugsperson(2l);
        n.markBestaetigt(bp1);
        n.markGeloest(bp2);
    }


    @Test
    public void markGeloest_Bestaetigt_Geloest()
    {
        final Status expected = Status.GELOEST;
        Status actual;

        InterfaceNotlage n = createInstance();
        Bezugsperson bp = new Bezugsperson();

        n.markBestaetigt(bp);
        n.markGeloest(bp);
        actual = n.getStatus();

        Assert.assertEquals(actual,expected);
    }


    @Test(expected = IllegalStateException.class)
    public void markGeloest_Geloest_IllegalStateException()
    {
        InterfaceNotlage n = createInstance();
        Bezugsperson bp = new Bezugsperson();
        n.markBestaetigt(bp);
        n.markGeloest(bp);
        n.markGeloest(bp);
    }

    @Test(expected = IllegalStateException.class)
    public void markGeloest_inBearbeitung_IllegalStateException()
    {
        InterfaceNotlage n = createInstance();
        Bezugsperson bp = new Bezugsperson();
        n.markGeloest(bp);
    }

    private InterfaceNotlage createDummyInstance()
    {
        return new Notlage();
    }

    private InterfaceNotlage createInstance()
    {
        DVP dvp = new DVP();
        Nachricht nachricht = new Nachricht("payload");
        return new Notlage(dvp, nachricht);
    }
}
