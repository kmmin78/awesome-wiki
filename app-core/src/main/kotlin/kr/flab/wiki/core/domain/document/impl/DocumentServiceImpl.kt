package kr.flab.wiki.core.domain.document.impl

import kr.flab.wiki.core.common.exception.document.InvalidBodyException
import kr.flab.wiki.core.common.exception.document.InvalidTitleException
import kr.flab.wiki.core.domain.document.Document
import kr.flab.wiki.core.domain.document.DocumentFormatPolicy
import kr.flab.wiki.core.domain.document.DocumentService
import kr.flab.wiki.core.domain.document.persistence.DocumentEntity
import kr.flab.wiki.core.domain.document.repository.DocumentRepository
import kr.flab.wiki.core.domain.user.User
import kr.flab.wiki.lib.time.utcNow

internal class DocumentServiceImpl(
    private val docsRepo: DocumentRepository,
    docsPolicy: DocumentFormatPolicy,
) : DocumentService {
    private val validator = DocumentValidator(docsPolicy)

    override fun saveDocument(title: String, body: String, creator: User): Document {
        if (!this.validator.isTitleValid(title)) {
            throw InvalidTitleException("Cannot create document with title '$title'")
        }

        if (!this.validator.isBodyValid(body)) {
            throw InvalidBodyException("Cannot create document with title '$title'")
        }

        val document = docsRepo.findByTitle(title).let {
            val now = utcNow()
            return@let DocumentEntity(
                title = title,
                body = body,
                creator = creator,
                createdAt = now,
                version = if (it == null) 1L else ++it.version
            )
        }

        return this.docsRepo.save(document)
    }

    override fun findDocumentsByTitle(title: String): MutableList<Document> {
        return docsRepo.findAllByTitle(title)
    }

    override fun getDocumentByTitle(title: String): Document {
        return docsRepo.getByTitle(title)
    }


}
