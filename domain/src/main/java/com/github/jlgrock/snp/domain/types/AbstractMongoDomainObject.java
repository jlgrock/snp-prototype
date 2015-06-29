package com.github.jlgrock.snp.domain.types;

import com.github.jlgrock.snp.apis.domain.MongoDomainObject;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotNull;

/**
 *
 */
public abstract class AbstractMongoDomainObject implements MongoDomainObject<ObjectId> {
    @NotNull
    private ObjectId id;

    protected AbstractMongoDomainObject() {
        id = ObjectId.get();
    }
    /**
     * @param pId the identifier object
     */
    public void setId(final ObjectId pId) {
        if (pId == null) {
            return;
        }
        id = pId;
    }

    @Override
    public ObjectId getId() {
        return id;
    }
}
