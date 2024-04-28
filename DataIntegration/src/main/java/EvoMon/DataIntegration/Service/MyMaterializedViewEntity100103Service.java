package EvoMon.DataIntegration.Service;

import EvoMon.DataIntegration.Model.MyMaterializedViewEntity100103;
import EvoMon.DataIntegration.Repository.MyMaterializedViewEntity100103Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyMaterializedViewEntity100103Service {

    @Autowired
    private MyMaterializedViewEntity100103Repository myMaterializedViewEntity100103Repository;

    public List<MyMaterializedViewEntity100103> getAllByCustomMaterial() {
        return  myMaterializedViewEntity100103Repository.findAll();
    }
}
